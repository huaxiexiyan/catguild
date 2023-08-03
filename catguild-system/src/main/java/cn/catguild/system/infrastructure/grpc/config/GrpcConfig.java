package cn.catguild.system.infrastructure.grpc.config;

import com.alibaba.cloud.nacos.registry.NacosRegistration;
import jakarta.annotation.PostConstruct;
import net.devh.boot.grpc.common.util.GrpcUtils;
import net.devh.boot.grpc.server.config.GrpcServerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Configuration;


/**
 * @author xiyan
 * @date 2023/8/2 18:40
 */
@ImportAutoConfiguration({
        //net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration.class,
        //net.devh.boot.grpc.client.autoconfigure.GrpcClientMetricAutoConfiguration.class,
        //net.devh.boot.grpc.client.autoconfigure.GrpcClientHealthAutoConfiguration.class,
        //net.devh.boot.grpc.client.autoconfigure.GrpcClientSecurityAutoConfiguration.class,
        //net.devh.boot.grpc.client.autoconfigure.GrpcClientTraceAutoConfiguration.class,
        //net.devh.boot.grpc.client.autoconfigure.GrpcDiscoveryClientAutoConfiguration.class,

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
@Configuration
public class GrpcConfig {
    @Autowired
    private NacosRegistration nacosRegistration;
    @Autowired
    private GrpcServerProperties grpcProperties;
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

}
