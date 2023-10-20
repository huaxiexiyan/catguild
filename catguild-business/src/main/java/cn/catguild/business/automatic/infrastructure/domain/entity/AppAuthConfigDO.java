package cn.catguild.business.automatic.infrastructure.domain.entity;

import cn.catguild.business.automatic.infrastructure.domain.type.AppAuthConfigStatus;
import cn.catguild.common.entity.jpa.BaseTenant;
import cn.catguild.common.type.ActiveStatus;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 网络各类app的授权信息 appAuth
 *
 * @author xiyan
 * @date 2023/9/19 11:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("business_automatic_app_auth_config")
public class AppAuthConfigDO extends BaseTenant {

    /**
     * 授权类型
     */
    private Long appAuthTypeId;

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
    private LocalDateTime lastUpdateAccessTokenTime;

    /**
     * 授权最后更新时间
     */
    private LocalDateTime lastUpdateRefreshTokenTime;

    private ActiveStatus activeStatus;

    private AppAuthConfigStatus status;

}
