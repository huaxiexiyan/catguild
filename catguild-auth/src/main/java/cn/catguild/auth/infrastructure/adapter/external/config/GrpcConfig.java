package cn.catguild.auth.infrastructure.adapter.external.config;

import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.common.utility.transplant.StringPool;
import com.google.common.annotations.VisibleForTesting;
import io.grpc.*;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;

/**
 * @author xiyan
 * @date 2023/8/2 18:40
 */
@Slf4j
@Configuration
@ImportAutoConfiguration({
        net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration.class,
        net.devh.boot.grpc.client.autoconfigure.GrpcClientMetricAutoConfiguration.class,
        net.devh.boot.grpc.client.autoconfigure.GrpcClientHealthAutoConfiguration.class,
        net.devh.boot.grpc.client.autoconfigure.GrpcClientSecurityAutoConfiguration.class,
        net.devh.boot.grpc.client.autoconfigure.GrpcClientTraceAutoConfiguration.class,
        net.devh.boot.grpc.client.autoconfigure.GrpcDiscoveryClientAutoConfiguration.class,

        net.devh.boot.grpc.common.autoconfigure.GrpcCommonCodecAutoConfiguration.class,
        net.devh.boot.grpc.common.autoconfigure.GrpcCommonTraceAutoConfiguration.class,
})
public class GrpcConfig {

    @VisibleForTesting
    static final Metadata.Key<String> AUTHORIZATION_HEADER_KEY =
            Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER);

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

}
