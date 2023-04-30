//package cn.catguild.auth.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
//import org.springframework.security.oauth2.core.OAuth2TokenValidator;
//import org.springframework.security.oauth2.jwt.Jwt;
//import org.springframework.security.oauth2.jwt.JwtClaimValidator;
//import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationConsentService;
//import org.springframework.security.oauth2.server.authorization.OAuth2AuthorizationService;
//import org.springframework.security.oauth2.server.authorization.authentication.JwtClientAssertionAuthenticationProvider;
//import org.springframework.security.oauth2.server.authorization.authentication.JwtClientAssertionDecoderFactory;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
//import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
//import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
//import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
//import org.springframework.security.oauth2.server.authorization.token.DelegatingOAuth2TokenGenerator;
//import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenGenerator;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.List;
//import java.util.function.Consumer;
//import java.util.function.Function;
//
///**
// * 配置 客户端 仓储
// *
// * @author xiyan
// * @date 2023/4/30 14:04
// */
//@Configuration
//public class AuthConfig {
//
//    @Autowired
//    RegisteredClientRepository registeredClientRepository;
//    @Autowired
//    AuthorizationServerSettings authorizationServerSettings;
//    @Autowired
//    OAuth2AuthorizationService authorizationService;
//    @Autowired
//    OAuth2AuthorizationConsentService authorizationConsentService;
//    @Autowired
//    OAuth2TokenGenerator<?> tokenGenerator;
//
//    /**
//     * 自定义 OAuth2 授权服务器的安全配置
//     *
//     * @param http
//     * @return
//     * @throws Exception
//     */
//    @Bean
//    @Order(Ordered.HIGHEST_PRECEDENCE)
//    public SecurityFilterChain authorizationServerSecurityFilterChain(HttpSecurity http) throws Exception {
//        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
//                new OAuth2AuthorizationServerConfigurer();
//        http.apply(authorizationServerConfigurer);
//
//        authorizationServerConfigurer
//                // 注册 客户端 仓储  (REQUIRED)
//                .registeredClientRepository(registeredClientRepository)
//                // 用于管理新授权和现有授权的OAuth2AuthorizationService。
//                .authorizationService(authorizationService)
//                // OAuth2AuthorizationConsentService用于管理新的和现有的授权同意。
//                .authorizationConsentService(authorizationConsentService)
//                // AuthorizationServerSettings，用于自定义OAuth2授权服务器的配置设置。(REQUIRED)
//                .authorizationServerSettings(authorizationServerSettings)
//                // 用于生成OAuth2授权服务器支持的令牌的OAuth2TokenGenerator。
//                .tokenGenerator(tokenGenerator)
//                // OAuth2客户端身份验证的配置程序。
//                .clientAuthentication(clientAuthentication ->
//                        clientAuthentication
//                                .authenticationProviders(configureJwtClientAssertionValidator()))
//                // OAuth2授权端点的配置程序。
//                .authorizationEndpoint(authorizationEndpoint -> { })
//                // OAuth2令牌端点的配置程序。
//                .tokenEndpoint(tokenEndpoint -> { })
//                // OAuth2令牌内省端点的配置程序。
//                .tokenIntrospectionEndpoint(tokenIntrospectionEndpoint -> { })
//                // OAuth2令牌撤销端点的配置程序。
//                .tokenRevocationEndpoint(tokenRevocationEndpoint -> { })
//                // OAuth2授权服务器元数据终结点的配置程序。
//                .authorizationServerMetadataEndpoint(authorizationServerMetadataEndpoint -> { })
//                .oidc(oidc -> oidc
//                        // OpenID Connect 1.0提供程序配置端点的配置程序。
//                        .providerConfigurationEndpoint(providerConfigurationEndpoint -> { })
//                        // OpenID Connect 1.0用户信息端点的配置程序。
//                        .userInfoEndpoint(userInfoEndpoint -> { })
//                        // OpenID Connect 1.0客户端注册端点的配置程序。
//                        .clientRegistrationEndpoint(clientRegistrationEndpoint -> { })
//                );
//
//        return http.build();
//    }
//
//    private Consumer<List<AuthenticationProvider>> configureJwtClientAssertionValidator() {
//        return (authenticationProviders) ->
//                authenticationProviders.forEach((authenticationProvider) -> {
//                    if (authenticationProvider instanceof JwtClientAssertionAuthenticationProvider) {
//                        // 自定义JwtClientAssertionDecoderFactory
//                        JwtClientAssertionDecoderFactory jwtDecoderFactory = new JwtClientAssertionDecoderFactory();
//                        Function<RegisteredClient, OAuth2TokenValidator<Jwt>> jwtValidatorFactory = (registeredClient) ->
//                                new DelegatingOAuth2TokenValidator<>(
//                                        // 使用默认验证器
//                                        JwtClientAssertionDecoderFactory.DEFAULT_JWT_VALIDATOR_FACTORY.apply(registeredClient),
//                                        // 添加自定义验证器
//                                        new JwtClaimValidator<>("claim", "value"::equals));
//                        jwtDecoderFactory.setJwtValidatorFactory(jwtValidatorFactory);
//
//                        ((JwtClientAssertionAuthenticationProvider) authenticationProvider)
//                                .setJwtDecoderFactory(jwtDecoderFactory);
//                    }
//                });
//    }
//
//}
