package cn.catguild.business.automatic.domain;

import cn.catguild.business.automatic.infrastructure.domain.type.AppAuthConfigStatus;
import cn.catguild.common.domain.BaseTenantBO;
import cn.catguild.common.type.ActiveStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 网络各类app的授权信息 appAuth
 *
 * @author xiyan
 * @date 2023/9/19 11:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppAuthConfig extends BaseTenantBO {

    /**
     * 授权类型
     */
    private AppAuthType appAuthType;

    /**
     * 授权token
     */
    private String accessToken;

    /**
     * 刷新token
     */
    private String refreshToken;

    /**
     * 过期时间
     */
    private Integer expiresIn;

    /**
     * 授权最后更新时间
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdateAccessTokenTime;

    /**
     * 授权最后更新时间
     */
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastUpdateRefreshTokenTime;

    private ActiveStatus activeStatus;

    private AppAuthConfigStatus status;

    // 批量清空授权接收id
    private List<Long> ids;

}
