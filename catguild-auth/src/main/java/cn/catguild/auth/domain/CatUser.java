package cn.catguild.auth.domain;

import cn.catguild.auth.domain.type.UserAuthorityType;
import cn.catguild.common.entity.jpa.BaseTenant;
import cn.catguild.common.type.ActiveStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/7/31 15:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_user`")
public class CatUser extends BaseTenant {

    @Comment("用户名")
    private String name;

    @Comment("用户角色类型-本身就代表着一种特殊的用户权限")
    @Enumerated(EnumType.STRING)
    private UserAuthorityType authorityType;

    @Comment("活跃状态")
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

}
