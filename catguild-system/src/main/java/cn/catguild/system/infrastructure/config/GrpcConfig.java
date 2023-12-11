package cn.catguild.system.infrastructure.config;

import cn.catguild.common.utility.transplant.StringPool;
import cn.catguild.system.util.AuthUtil;
import com.alibaba.cloud.nacos.registry.NacosRegistration;
import com.google.common.annotations.VisibleForTesting;
import io.grpc.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import net.devh.boot.grpc.common.util.GrpcUtils;
import net.devh.boot.grpc.server.config.GrpcServerProperties;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import net.devh.boot.grpc.server.security.authentication.BearerAuthenticationReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.util.StringUtils;


/**
 * @author xiyan
 * @date 2023/8/2 18:40
 */
@ImportAutoConfiguration({
        net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration.class,
        net.devh.boot.grpc.client.autoconfigure.GrpcClientMetricAutoConfiguration.class,
        net.devh.boot.grpc.client.autoconfigure.GrpcClientHealthAutoConfiguration.class,
        net.devh.boot.grpc.client.autoconfigure.GrpcClientSecurityAutoConfiguration.class,
        net.devh.boot.grpc.client.autoconfigure.GrpcClientTraceAutoConfiguration.class,
        net.devh.boot.grpc.client.autoconfigure.GrpcDiscoveryClientAutoConfiguration.class,

        net.devh.boot.grpc.common.autoconfigure.GrpcCommonCodecAutoConfiguration.class,
        net.devh.boot.grpc.common.autoconfigure.GrpcCommonTraceAutoConfiguration.class,

        net.devh.boot.grpc.server.autoconfigure.GrpcAdviceAutoConfiguration.class,
        net.devh.boot.grpc.server.autoconfigure.GrpcHealthServiceAutoConfiguration.class,
        net.devh.boot.grpc.server.autoconfigure.GrpcReflectionServiceAutoConfiguration.class,
        net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration.class,
        net.devh.boot.grpc.server.autoconfigure.GrpcServerFactoryAutoConfiguration.class,
        net.devh.boot.grpc.server.autoconfigure.GrpcServerMetricAutoConfiguration.class,
        net.devh.boot.grpc.server.autoconfigure.GrpcServerSecurityAutoConfiguration.class,
        net.devh.boot.grpc.server.autoconfigure.GrpcServerTraceAutoConfiguration.class

        // 以下注册中心相关的自动配置，在jdk17 @PostConstruct 不兼容，需要要重写
        //net.devh.boot.grpc.server.autoconfigure.GrpcMetadataConsulConfiguration.class,
        //net.devh.boot.grpc.server.autoconfigure.GrpcMetadataEurekaConfiguration.class,
        //net.devh.boot.grpc.server.autoconfigure.GrpcMetadataNacosConfiguration.class,
        //net.devh.boot.grpc.server.autoconfigure.GrpcMetadataZookeeperConfiguration.class,
})
@Slf4j
@Configuration
public class GrpcConfig {

    @Autowired
    private NacosRegistration nacosRegistration;
    @Autowired
    private GrpcServerProperties grpcProperties;
    @Autowired
    private JwtDecoder jwtDecoder;

    @VisibleForTesting
    static final Metadata.Key<String> AUTHORIZATION_HEADER_KEY =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

    @PostConstruct
    public void init() {
        if (nacosRegistration != null) {
            final int port = grpcProperties.getPort();
            if (GrpcUtils.INTER_PROCESS_DISABLE != port) {
                nacosRegistration.getMetadata()
                        .put(GrpcUtils.CLOUD_DISCOVERY_METADATA_PORT, Integer.toString(port));
            }
        }
    }


    @Bean
    BearerAuthenticationReader bearerAuthenticationReader(){
        return new BearerAuthenticationReader(BearerTokenAuthenticationToken::new);
    }

    @GrpcGlobalClientInterceptor
    ClientInterceptor tokenClientInterceptor() {
        return new ClientInterceptor() {
            @Override
            public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method,
                                                                       CallOptions callOptions, Channel next) {
                return new ForwardingClientCall.SimpleForwardingClientCall<ReqT, RespT>(next.newCall(method, callOptions)) {
                    @Override
                    public void start(Listener<RespT> responseListener, Metadata headers) {
                        /* put custom header */
                        /** 添加 **/
                        headers.put(AUTHORIZATION_HEADER_KEY, OAuth2AccessToken.TokenType.BEARER.getValue() + StringPool.SPACE + AuthUtil.getTokenValue());
                        super.start(new ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT>(responseListener) {
                            @Override
                            public void onHeaders(Metadata headers) {
                                /**
                                 * if you don't need receive header from server,
                                 * you can use {@link io.grpc.stub.MetadataUtils#attachHeaders}
                                 * directly to send header
                                 */
                                log.debug("header received from server:" + headers);
                                super.onHeaders(headers);
                            }
                        }, headers);
                    }
                };
            }
        };
    }

    @GrpcGlobalServerInterceptor
    ServerInterceptor tokenServerInterceptor() {
        return new ServerInterceptor() {
            @Override
            public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call,
                                                                         Metadata headers,
                                                                         ServerCallHandler<ReqT, RespT> next) {
                log.info("header received from client:" + headers);
                String tokenValue = headers.get(AUTHORIZATION_HEADER_KEY);
                if (StringUtils.hasText(tokenValue)){
                    log.debug("解析出来的token:【{}】",tokenValue);
                    parseBearerTokenAuthorization(tokenValue.replace(OAuth2AccessToken.TokenType.BEARER.getValue() + StringPool.SPACE,""));
                }else {
                    log.warn("缺失授权token");
                    throw new OAuth2AuthenticationException("缺失授权token");
                }

                return next.startCall(new ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT>(call) {
                    @Override
                    public void sendHeaders(Metadata responseHeaders) {
                        responseHeaders.put(AUTHORIZATION_HEADER_KEY, tokenValue);
                        super.sendHeaders(responseHeaders);
                    }
                }, headers);
            }

            private void parseBearerTokenAuthorization(String tokenValue) {
                JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtDecoder);
                BearerTokenAuthenticationToken bearer = new BearerTokenAuthenticationToken(tokenValue);
                Authentication authenticate = jwtAuthenticationProvider.authenticate(bearer);
                log.debug("Authenticated token");
                // 将解析好的权限访问上下文
                SecurityContextHolder.getContext().setAuthentication(authenticate);
            }
        };
    }

}
