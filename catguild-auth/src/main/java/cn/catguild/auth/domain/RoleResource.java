package cn.catguild.auth.domain;

import cn.catguild.common.entity.jpa.BaseTenant;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/10/8 14:09
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_role_resource`")
public class RoleResource extends BaseTenant {

    @Comment("角色id")
    private Long roleId;

    @Comment("资源id")
    private Long resourceId;

}
