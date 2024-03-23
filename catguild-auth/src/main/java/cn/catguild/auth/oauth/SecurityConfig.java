package cn.catguild.auth.oauth;

import cn.catguild.auth.domain.CatUser;
import cn.catguild.auth.oauth.grant.PasswordGrantAuthenticationConverter;
import cn.catguild.auth.oauth.grant.PasswordGrantAuthenticationProvider;
import cn.catguild.auth.oauth.grant.PasswordGrantAuthenticationToken;
import cn.catguild.auth.oauth.service.CatUserDetailsService;
import cn.catguild.common.constant.TokenConstant;
import cn.catguild.common.utility.IdUtil;
import cn.catguild.common.utility.RSAUtils;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2ClientAuthenticationToken;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.*;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @author xiyan
 * @date 2023/5/31 10:30
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private CatUserDetailsService userDetailsService;

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain authorizationServerSecurityFilterChain(
            HttpSecurity http,
            OAuth2AuthorizationService authorizationService,
            OAuth2TokenGenerator<?> tokenGenerator)
            throws Exception {

        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
                new OAuth2AuthorizationServerConfigurer();

        PasswordGrantAuthenticationProvider passwordGrantAuthenticationProvider = new PasswordGrantAuthenticationProvider(authorizationService, tokenGenerator);
        passwordGrantAuthenticationProvider.setUserDetailsService(userDetailsService);
        authorizationServerConfigurer.tokenEndpoint(
                tokenEndpoint ->
                        tokenEndpoint
                                .accessTokenRequestConverter(new PasswordGrantAuthenticationConverter())
                                .authenticationProvider(passwordGrantAuthenticationProvider));

        RequestMatcher endpointsMatcher = authorizationServerConfigurer.getEndpointsMatcher();

		// 此处是进一步判断具体权限，比如角色之类的？
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/password").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login/token").permitAll()
                        .requestMatchers(endpointsMatcher).permitAll())
				// 下面是选择是否要鉴权的请求，仅判断token是否有效？
                .oauth2ResourceServer(oauth2ResourceServer -> oauth2ResourceServer
                        .jwt(Customizer.withDefaults()))
                .csrf(csrf -> csrf.ignoringRequestMatchers(endpointsMatcher).disable())
                .apply(authorizationServerConfigurer);;

        return http.build();
    }

    //@Bean
    //@Order(2)
    //public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http)
    //        throws Exception {
    //    http.oauth2ResourceServer(oauth2ResourceServer ->
    //                    oauth2ResourceServer.jwt(Customizer.withDefaults()))
    //            .sessionManagement((session) -> session
    //                    // 无状态不存储在会话中
    //                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    //            );
    //
    //    return http.build();
    //}


    @Bean
    OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource) {
        JwtGenerator jwtGenerator = new JwtGenerator(new NimbusJwtEncoder(jwkSource));
        jwtGenerator.setJwtCustomizer(jwtCustomizer());
        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
        return new DelegatingOAuth2TokenGenerator(
                jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource(@Value("${custom.catguild-auth.rsa.public-key}") org.springframework.core.io.Resource publicKey,
                                                @Value("${custom.catguild-auth.rsa.private-key}") org.springframework.core.io.Resource privateKey,
                                                @Value("${custom.catguild-auth.rsa.key-id}") String keyId) {
        RSAKey rsaKey = new RSAKey.Builder((RSAPublicKey) RSAUtils.getPublicKeyFromResource(publicKey))
                .privateKey((RSAPrivateKey) RSAUtils.getPrivateKeyFromResource(privateKey))
                .keyID(keyId)
                .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings(
            @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}") String issuer) {
        return AuthorizationServerSettings.builder()
                .issuer(issuer)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            JwtClaimsSet.Builder claims = context.getClaims();
            OAuth2ClientAuthenticationToken principal = context.getPrincipal();
            principal.getName();
            Authentication authorizationGrant = context.getAuthorizationGrant();
            if (authorizationGrant instanceof PasswordGrantAuthenticationToken authenticationToken) {
                // 添加租户、用户id、不可添加复杂内容
                String username = authenticationToken.getUsername();
                CatUser user = userDetailsService.getByUsername(username);
                // 添加登录用户信息
                claims.claim(TokenConstant.USER_ID, IdUtil.obfuscate(user.getId()));
                claims.claim(TokenConstant.TENANT_ID, IdUtil.obfuscate(user.getTenantId()));
                claims.claim(TokenConstant.AUTHORITY_TYPE, user.getAuthorityType());
            }
        };
    }


    @Component
    public class FailureEvents {
        @EventListener
        public void onFailure(AuthenticationFailureBadCredentialsEvent badCredentials) {
            if (badCredentials.getAuthentication() instanceof BearerTokenAuthenticationToken) {
                // ... handle
                log.info("令牌失败 =================================================== >>>> ");
            }
        }
    }
}
