package cn.catguild.auth.domain;

import cn.catguild.common.entity.jpa.BaseTenant;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/10/8 14:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_user_role`")
public class UserRole extends BaseTenant {

    @Comment("用户id")
    private Long userId;

    @Comment("角色id")
    private Long roleId;

}
