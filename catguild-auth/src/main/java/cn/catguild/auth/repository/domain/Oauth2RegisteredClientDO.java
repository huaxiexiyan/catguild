package cn.catguild.auth.repository.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.Instant;

/**
 * @author xiyan
 * @date 2023/4/30 15:55
 */
@TableName("oauth2_registered_client")
@Data
public class Oauth2RegisteredClientDO {

    // 唯一标识 的 ID RegisteredClient
    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private Long guildId;
    // '客户端标识符'
    private String clientId;
    // '发出客户端标识符的时间'
    private Instant clientIdIssuedAt;
    // 客户的秘密。该值应该使用 Spring Security 的PasswordEncoder进行编码
    private String clientSecret;
    // 客户端密码过期的时间
    private Instant clientSecretExpiresAt;
    // 用于客户端的描述性名称。该名称可能会在某些情况下使用，例如在同意页面中显示客户端名称时
    private String clientName;
    // 客户端可能使用的身份验证方法。支持的值为client_secret_basic, client_secret_post, private_key_jwt
    private String clientAuthenticationMethods;
    // 客户端可以使用的授权授予类型。支持的值为authorization_code、client_credentials和refresh_token
    private String authorizationGrantTypes;
    // 客户端可以在基于重定向的流中使用的已注册重定向 URI——例如，authorization_code grant
    private String redirectUris;
    // '允许客户端请求的范围'
    private String scopes;
    // 客户端的自定义设置——例如，需要PKCE、需要授权同意等
    private String clientSettings;
    // 发给客户端的 OAuth2 令牌的自定义设置——例如，访问/刷新令牌生存时间、重用刷新令牌等
    private String tokenSettings;

}
