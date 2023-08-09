package cn.catguild.user.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

/**
 * @author xiyan
 * @date 2023/8/8 15:48
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.addAllowedOriginPattern("http://localhost:3002"); // 设置允许的源
        corsConfig.addAllowedMethod("*"); // 设置允许的HTTP方法
        corsConfig.addAllowedHeader("*"); // 设置允许的头部
        corsConfig.setAllowCredentials(true);

        // {"http://localhost:3002", "http://example.com"}

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
