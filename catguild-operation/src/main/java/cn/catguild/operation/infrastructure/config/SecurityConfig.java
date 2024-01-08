package cn.catguild.operation.infrastructure.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

/**
 * @author xiyan
 * @date 2023/5/31 10:30
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
            throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login").permitAll()
                .anyRequest().authenticated()
        ).oauth2ResourceServer(oauth2ResourceServer ->
                oauth2ResourceServer.jwt(Customizer.withDefaults())
                        .authenticationEntryPoint((request, response, exception) -> {
                            // oauth2 认证失败导致的，还有一种可能是非oauth2认证失败导致的，比如没有传递token，但是访问受权限保护的方法
                            if (exception instanceof OAuth2AuthenticationException oAuth2AuthenticationException) {
                                OAuth2Error error = oAuth2AuthenticationException.getError();
                                log.info("认证失败,异常类型:[{}],异常:[{}]", exception.getClass().getName(), error);
                            }
                            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                            response.setContentType(MediaType.APPLICATION_JSON.toString());
                            response.getWriter().write("{\"code\":-3,\"message\":\"您无权限访问\"}");
                        })
                        // 认证成功后，无权限访问
                        .accessDeniedHandler((request, response, exception) -> {
                            log.info("您无权限访问,异常类型:[{}]", exception.getClass().getName());
                            response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                            response.setContentType(MediaType.APPLICATION_JSON.toString());
                            response.getWriter().write("{\"code\":-4,\"message\":\"您无权限访问\"}");
                        })
        ).sessionManagement((session) ->
                // 无状态不存储在会话中
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    //@Bean
    //JwtDecoder jwtDecoder() {
    //    NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withIssuerLocation(issuer).build();
    //    return jwtDecoder;
    //}

    @Bean
    RestTemplate rest() {
        RestTemplate rest = new RestTemplate();
        rest.getInterceptors().add((request, body, execution) -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication == null) {
                return execution.execute(request, body);
            }

            if (!(authentication.getCredentials() instanceof AbstractOAuth2Token)) {
                return execution.execute(request, body);
            }

            AbstractOAuth2Token token = (AbstractOAuth2Token) authentication.getCredentials();
            request.getHeaders().setBearerAuth(token.getTokenValue());
            return execution.execute(request, body);
        });
        return rest;
    }

    @Component
    public class FailureEvents {
        @EventListener
        public void onFailure(AuthenticationFailureBadCredentialsEvent badCredentials) {
            if (badCredentials.getAuthentication() instanceof BearerTokenAuthenticationToken) {
                // ... handle
                log.warn("令牌失败");
            }
        }
    }

}
