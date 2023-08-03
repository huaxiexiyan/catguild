DROP TABLE IF EXISTS oauth2_registered_client;
CREATE TABLE oauth2_registered_client
(
    id                            varchar(100)                            NOT NULL COMMENT '唯一标识 的 ID RegisteredClient',
    guild_id                      long                                    NOT NULL COMMENT '租户id',
    client_id                     varchar(100)                            NOT NULL COMMENT '客户端标识符',
    client_id_issued_at           timestamp     DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '发出客户端标识符的时间',
    client_secret                 varchar(200)  DEFAULT NULL COMMENT '客户的秘密。该值应该使用 Spring Security 的PasswordEncoder进行编码',
    client_secret_expires_at      timestamp     DEFAULT NULL COMMENT '客户端密码过期的时间',
    client_name                   varchar(200)                            NOT NULL COMMENT '用于客户端的描述性名称。该名称可能会在某些情况下使用，例如在同意页面中显示客户端名称时',
    client_authentication_methods varchar(1000)                           NOT NULL COMMENT '客户端可能使用的身份验证方法。支持的值为client_secret_basic, client_secret_post, private_key_jwt',
    authorization_grant_types     varchar(1000)                           NOT NULL COMMENT '客户端可以使用的授权授予类型。支持的值为authorization_code、client_credentials和refresh_token',
    redirect_uris                 varchar(1000) DEFAULT NULL COMMENT '客户端可以在基于重定向的流中使用的已注册重定向 URI——例如，authorization_code grant',
    post_logout_redirect_uris     varchar(1000) DEFAULT NULL,
    scopes                        varchar(1000)                           NOT NULL COMMENT '允许客户端请求的范围',
    client_settings               varchar(2000)                           NOT NULL COMMENT '客户端的自定义设置——例如，需要PKCE、需要授权同意等',
    token_settings                varchar(2000)                           NOT NULL COMMENT '发给客户端的 OAuth2 令牌的自定义设置——例如，访问/刷新令牌生存时间、重用刷新令牌等',
    PRIMARY KEY (id)
);

CREATE TABLE client (
    id varchar(255) NOT NULL,
    clientId varchar(255) NOT NULL,
    clientIdIssuedAt timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
    clientSecret varchar(255) DEFAULT NULL,
    clientSecretExpiresAt timestamp DEFAULT NULL,
    clientName varchar(255) NOT NULL,
    clientAuthenticationMethods varchar(1000) NOT NULL,
    authorizationGrantTypes varchar(1000) NOT NULL,
    redirectUris varchar(1000) DEFAULT NULL,
    postLogoutRedirectUris varchar(1000) DEFAULT NULL,
    scopes varchar(1000) NOT NULL,
    clientSettings varchar(2000) NOT NULL,
    tokenSettings varchar(2000) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE authorization (
   id varchar(255) NOT NULL,
   registeredClientId varchar(255) NOT NULL,
   principalName varchar(255) NOT NULL,
   authorizationGrantType varchar(255) NOT NULL,
   authorizedScopes varchar(1000) DEFAULT NULL,
   attributes varchar(4000) DEFAULT NULL,
   state varchar(500) DEFAULT NULL,
   authorizationCodeValue varchar(4000) DEFAULT NULL,
   authorizationCodeIssuedAt timestamp DEFAULT NULL,
   authorizationCodeExpiresAt timestamp DEFAULT NULL,
   authorizationCodeMetadata varchar(2000) DEFAULT NULL,
   accessTokenValue varchar(4000) DEFAULT NULL,
   accessTokenIssuedAt timestamp DEFAULT NULL,
   accessTokenExpiresAt timestamp DEFAULT NULL,
   accessTokenMetadata varchar(2000) DEFAULT NULL,
   accessTokenType varchar(255) DEFAULT NULL,
   accessTokenScopes varchar(1000) DEFAULT NULL,
   refreshTokenValue varchar(4000) DEFAULT NULL,
   refreshTokenIssuedAt timestamp DEFAULT NULL,
   refreshTokenExpiresAt timestamp DEFAULT NULL,
   refreshTokenMetadata varchar(2000) DEFAULT NULL,
   oidcIdTokenValue varchar(4000) DEFAULT NULL,
   oidcIdTokenIssuedAt timestamp DEFAULT NULL,
   oidcIdTokenExpiresAt timestamp DEFAULT NULL,
   oidcIdTokenMetadata varchar(2000) DEFAULT NULL,
   oidcIdTokenClaims varchar(2000) DEFAULT NULL,
   userCodeValue varchar(4000) DEFAULT NULL,
   userCodeIssuedAt timestamp DEFAULT NULL,
   userCodeExpiresAt timestamp DEFAULT NULL,
   userCodeMetadata varchar(2000) DEFAULT NULL,
   deviceCodeValue varchar(4000) DEFAULT NULL,
   deviceCodeIssuedAt timestamp DEFAULT NULL,
   deviceCodeExpiresAt timestamp DEFAULT NULL,
   deviceCodeMetadata varchar(2000) DEFAULT NULL,
   PRIMARY KEY (id)
);

CREATE TABLE authorizationConsent (
  registeredClientId varchar(255) NOT NULL,
  principalName varchar(255) NOT NULL,
  authorities varchar(1000) NOT NULL,
  PRIMARY KEY (registeredClientId, principalName)
);
