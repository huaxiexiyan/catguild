package cn.catguild.auth.domain;

import cn.catguild.auth.domain.type.NetworkAppType;
import cn.catguild.common.entity.jpa.BaseTenant;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private NetworkAppType networkAppType;

    @Comment("授权token")
    @Column(columnDefinition = "TEXT")
    private String accessToken;

    @Comment("刷新token")
    @Column(columnDefinition = "TEXT")
    private String refreshToken;

    @Comment("过期时间")
    private Integer expiresIn;

}
