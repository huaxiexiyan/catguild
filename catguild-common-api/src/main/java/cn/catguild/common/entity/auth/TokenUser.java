package cn.catguild.common.entity.auth;

import cn.catguild.common.type.auth.UserAuthorityType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author xiyan
 * @date 2023/10/8 13:47
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class TokenUser {

    private Long tenantId;

    private Long userId;

    private UserAuthorityType authorityType;

}
