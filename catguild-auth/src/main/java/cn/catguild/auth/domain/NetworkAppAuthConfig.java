package cn.catguild.auth.domain;

import cn.catguild.auth.domain.type.NetworkAppType;
import cn.catguild.common.entity.jpa.BaseTenant;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * 网络各类app的授权信息
 *
 * @author xiyan
 * @date 2023/9/19 11:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_network_app_auth_config`")
public class NetworkAppAuthConfig extends BaseTenant {

    @Comment("关联的用户id")
    private Long userId;

    @Comment("网络app类型")
    private NetworkAppType networkAppType;

    @Comment("授权token")
    private String accessToken;

    @Comment("刷新token")
    private String refreshToken;


}
