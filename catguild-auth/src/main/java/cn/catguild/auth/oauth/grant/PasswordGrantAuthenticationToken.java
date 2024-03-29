package cn.catguild.auth.oauth.grant;

import lombok.Getter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.authentication.OAuth2AuthorizationGrantAuthenticationToken;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author xiyan
 * @date 2023/5/31 10:24
 */
public class PasswordGrantAuthenticationToken extends OAuth2AuthorizationGrantAuthenticationToken {

    @Getter
    private final String username;
    @Getter
    private final String password;
    @Getter
    private final String tenantId;

    /**
     * Constructs an {@code PasswordGrantAuthenticationToken} using the provided parameters.
     *
     * @param username 凭证名
     * @param password 凭证密码
     * @param tenantId 租户id
     * @param grantType 授权类型
     * @param clientPrincipal the authenticated client principal
     * @param additionalParameters the additional parameters
     */
    public PasswordGrantAuthenticationToken(String username,String password,String tenantId,
                                            String grantType,Authentication clientPrincipal,
                                            @Nullable Map<String, Object> additionalParameters) {
        super(new AuthorizationGrantType(grantType), clientPrincipal, additionalParameters);
        Assert.hasText(username, "username cannot be empty");
        Assert.hasText(password, "password cannot be empty");
        Assert.hasText(tenantId, "tenantId cannot be empty");
        this.username = username;
        this.password = password;
        this.tenantId = tenantId;
    }




}
