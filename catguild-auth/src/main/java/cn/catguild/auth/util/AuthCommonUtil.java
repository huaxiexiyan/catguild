package cn.catguild.auth.util;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiyan
 * @date 2023/4/30 16:47
 */
public class AuthCommonUtil {
    private final static Map<String, ClientAuthenticationMethod> clientAuthenticationMethodMap = new HashMap<>();
    private final static Map<String, AuthorizationGrantType> authorizationGrantTypeMap = new HashMap<>();

    static {
        clientAuthenticationMethodMap.put("client_secret_basic", ClientAuthenticationMethod.CLIENT_SECRET_BASIC);
        clientAuthenticationMethodMap.put("client_secret_post", ClientAuthenticationMethod.CLIENT_SECRET_POST);
        clientAuthenticationMethodMap.put("client_secret_jwt", ClientAuthenticationMethod.CLIENT_SECRET_JWT);
        clientAuthenticationMethodMap.put("private_key_jwt", ClientAuthenticationMethod.PRIVATE_KEY_JWT);
        clientAuthenticationMethodMap.put("none", ClientAuthenticationMethod.NONE);

        authorizationGrantTypeMap.put("authorization_code",AuthorizationGrantType.AUTHORIZATION_CODE);
        authorizationGrantTypeMap.put("refresh_token",AuthorizationGrantType.REFRESH_TOKEN);
        authorizationGrantTypeMap.put("client_credentials",AuthorizationGrantType.CLIENT_CREDENTIALS);
    }

    public static ClientAuthenticationMethod getClientAuthenticationMethod(String str) {
        return clientAuthenticationMethodMap.getOrDefault(str,null);
    }

    public static AuthorizationGrantType getAuthorizationGrantType(String str) {
        return authorizationGrantTypeMap.getOrDefault(str, null);
    }

}
