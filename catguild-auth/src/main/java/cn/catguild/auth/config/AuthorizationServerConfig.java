///*
// * Copyright 2020-2023 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package cn.catguild.auth.config;
//
//import cn.catguild.auth.jose.Jwks;
//import com.nimbusds.jose.jwk.JWKSet;
//import com.nimbusds.jose.jwk.RSAKey;
//import com.nimbusds.jose.jwk.source.JWKSource;
//import com.nimbusds.jose.proc.SecurityContext;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
//import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
//import org.springframework.security.oauth2.core.AuthorizationGrantType;
//import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
//import org.springframework.security.oauth2.core.oidc.OidcScopes;
//import org.springframework.security.oauth2.jwt.JwtDecoder;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
//import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationConsentService;
//import org.springframework.security.oauth2.server.authorization.JdbcOAuth2AuthorizationService;
//import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
//import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
//import org.springframework.security.oauth2.server.authorization.client.JdbcRegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
//import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
//import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
//import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
//import org.springframework.security.oauth2.server.authorization.token.*;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
//
//import java.util.UUID;
//
///**
// * @author Joe Grandja
// * @author Dmitriy Dubson
// * @since 0.0.1
// */
//@Configuration(proxyBeanMethods = false)
//public class AuthorizationServerConfig {
//
//
//    /**
//     * 授权端点过滤链
//     *
//     * @param http
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//        OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);
//        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
//                .oidc(Customizer.withDefaults());    // Enable OpenID Connect 1.0
//
//        // @formatter:off
//        http.exceptionHandling(exceptions ->
//                // 未通过身份验证时，重定向到登录页面授权端点
//                        exceptions.authenticationEntryPoint(
//                                new LoginUrlAuthenticationEntryPoint("/login")))
//                .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
//        // @formatter:on
//        return http.build();
//    }
//
//
//    /**
//     * 注册客户端存储库
//     *
//     * @param jdbcTemplate
//     * @return
//     */
//    @Bean
//    public RegisteredClientRepository registeredClientRepository(JdbcTemplate jdbcTemplate) {
//        RegisteredClient registeredClient = RegisteredClient.withId(UUID.randomUUID().toString())
//                .clientId("messaging-client")
//                .clientSecret("{noop}secret")
//                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
//                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
//                .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
//                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
//                .redirectUri("http://127.0.0.1:8080/login/oauth2/code/messaging-client-oidc")
//                .redirectUri("http://www.baidu.com")
//                //.postLogoutRedirectUri("http://127.0.0.1:8080/logged-out")
//                .scope(OidcScopes.OPENID)
//                .scope(OidcScopes.PROFILE)
//                .scope("message.read")
//                .scope("message.write")
//                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
//                .build();
//
//        // Save registered client in db as if in-memory
//        JdbcRegisteredClientRepository registeredClientRepository = new JdbcRegisteredClientRepository(jdbcTemplate);
//        registeredClientRepository.save(registeredClient);
//
//        return registeredClientRepository;
//    }
//    // @formatter:on
//
//    /**
//     * OAuth2授权服务
//     * 存储新授权和查询现有授权的中央组件
//     *
//     * @param jdbcTemplate
//     * @param registeredClientRepository
//     * @return
//     */
//    @Bean
//    public OAuth2AuthorizationService authorizationService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
//        return new JdbcOAuth2AuthorizationService(jdbcTemplate, registeredClientRepository);
//    }
//
//    /**
//     * 存储新授权许可和查询现有授权许可的中央组件
//     *
//     * @param jdbcTemplate
//     * @param registeredClientRepository
//     * @return
//     */
//    @Bean
//    public OAuth2AuthorizationConsentService authorizationConsentService(JdbcTemplate jdbcTemplate, RegisteredClientRepository registeredClientRepository) {
//        return new JdbcOAuth2AuthorizationConsentService(jdbcTemplate, registeredClientRepository);
//    }
//
//    @Bean
//    public OAuth2TokenGenerator<?> tokenGenerator(JWKSource<SecurityContext> jwkSource) {
//        JwtEncoder jwtEncoder = new NimbusJwtEncoder(jwkSource);
//        JwtGenerator jwtGenerator = new JwtGenerator(jwtEncoder);
//        OAuth2AccessTokenGenerator accessTokenGenerator = new OAuth2AccessTokenGenerator();
//        OAuth2RefreshTokenGenerator refreshTokenGenerator = new OAuth2RefreshTokenGenerator();
//        return new DelegatingOAuth2TokenGenerator(
//                jwtGenerator, accessTokenGenerator, refreshTokenGenerator);
//    }
//
//    @Bean
//    public JWKSource<SecurityContext> jwkSource() {
//        RSAKey rsaKey = Jwks.generateRsa();
//        JWKSet jwkSet = new JWKSet(rsaKey);
//        return (jwkSelector, securityContext) -> jwkSelector.select(jwkSet);
//    }
//
//    @Bean
//    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
//        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
//    }
//
//    /**
//     * AuthorizationServerSettings 包含OAuth2授权服务器的配置设置。
//     * 它指定协议端点的URI以及颁发者标识符。
//     *
//     * @return
//     */
//    @Bean
//    public AuthorizationServerSettings authorizationServerSettings() {
//        return AuthorizationServerSettings.builder()
//                // 设置授权服务器用作其颁发者标识符的URL。没有配置，则从当前请求中解析
//                //.issuer("https://example.com")
//                .authorizationEndpoint("/oauth2/v1/authorize")
//                .tokenEndpoint("/oauth2/v1/token")
//                .tokenIntrospectionEndpoint("/oauth2/v1/introspect")
//                .tokenRevocationEndpoint("/oauth2/v1/revoke")
//                .jwkSetEndpoint("/oauth2/v1/jwks")
//                .oidcUserInfoEndpoint("/connect/v1/userinfo")
//                .oidcClientRegistrationEndpoint("/connect/v1/register")
//                .build();
//    }
//
//    @Bean
//    public EmbeddedDatabase embeddedDatabase() {
//        // @formatter:off
//        return new EmbeddedDatabaseBuilder()
//                .generateUniqueName(true)
//                .setType(EmbeddedDatabaseType.H2)
//                .setScriptEncoding("UTF-8")
//                .addScript("org/springframework/security/oauth2/server/authorization/oauth2-authorization-schema.sql")
//                .addScript("org/springframework/security/oauth2/server/authorization/oauth2-authorization-consent-schema.sql")
//                .addScript("org/springframework/security/oauth2/server/authorization/client/oauth2-registered-client-schema.sql")
//                .build();
//        // @formatter:on
//    }
//
//    /**
//     * 提供自定义OAuth2 客户端身份验证的能力。
//     * 它定义了扩展点，使您可以自定义客户端身份验证请求的预处理、主处理和后处理逻辑。
//     */
//    //@Bean
//    //public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//    //    OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
//    //            new OAuth2AuthorizationServerConfigurer();
//    //    http.apply(authorizationServerConfigurer);
//    //
//    //    authorizationServerConfigurer
//    //            .clientAuthentication(clientAuthentication ->
//    //                    clientAuthentication
//    //                            .authenticationConverter(authenticationConverter)
//    //                            .authenticationConverters(authenticationConvertersConsumer)
//    //                            .authenticationProvider(authenticationProvider)
//    //                            .authenticationProviders(authenticationProvidersConsumer)
//    //                            .authenticationSuccessHandler(authenticationSuccessHandler)
//    //                            .errorResponseHandler(errorResponseHandler)
//    //            );
//    //
//    //    return http.build();
//    //}
//
//}
