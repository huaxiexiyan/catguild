package cn.catguild.auth.domain;

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
 * @date 2023/10/8 14:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_role`")
public class CatRole extends BaseTenant {

    @Comment("角色名")
    private String name;

    @Comment("角色名唯一code")
    private String code;

    @Comment("活跃状态")
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

}