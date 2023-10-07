package cn.catguild.user.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Collections;

/**
 * @author xiyan
 * @date 2023/8/8 15:48
 */
@Configuration
public class CorsConfig {

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        //1,允许任何来源
        corsConfig.setAllowedOriginPatterns(Collections.singletonList("*"));
        //corsConfig.addAllowedOriginPattern("http://localhost:3002"); // 设置允许特定来源
        corsConfig.addAllowedMethod(CorsConfiguration.ALL); // 设置允许的HTTP方法
        corsConfig.addAllowedHeader(CorsConfiguration.ALL); // 设置允许的头部
        corsConfig.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfig);

        return new CorsWebFilter(source);
    }
}
