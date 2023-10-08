package cn.catguild.auth.oauth.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

/**
 * @author xiyan
 * @date 2023/8/16 14:37
 */
public class AuthUtil {

    public static Long getLoginId(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication instanceof JwtAuthenticationToken token){
            return Long.parseLong(token.getTokenAttributes().get("userId").toString());
        }
        return null;
    }
    public static Long getTenantId(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication instanceof JwtAuthenticationToken token){
            return Long.parseLong(token.getTokenAttributes().get("tenantId").toString());
        }
        return null;
    }


}
