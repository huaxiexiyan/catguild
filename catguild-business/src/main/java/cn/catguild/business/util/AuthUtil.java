package cn.catguild.business.util;



import cn.catguild.common.constant.TokenConstant;
import cn.catguild.common.entity.auth.TokenUser;
import cn.catguild.common.type.auth.UserAuthorityType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.Map;

/**
 * @author xiyan
 * @date 2023/8/16 14:37
 */
public class AuthUtil {

    public static Long getLoginId() {
        return getTokenUser().getUserId();
    }

    public static Long getTenantId() {
        return getTokenUser().getTenantId();
    }

    public static TokenUser getTokenUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        if (authentication instanceof JwtAuthenticationToken token) {
            Map<String, Object> tokenAttributes = token.getTokenAttributes();
            Long userId = Long.parseLong(tokenAttributes.get(TokenConstant.USER_ID).toString());
            Long tenantId = Long.parseLong(tokenAttributes.get(TokenConstant.TENANT_ID).toString());
            UserAuthorityType userAuthorityType = UserAuthorityType.valueOf(tokenAttributes.get(TokenConstant.AUTHORITY_TYPE).toString());
            return new TokenUser(tenantId, userId, userAuthorityType);
        }
        return new TokenUser();
    }

}
