package cn.catguild.auth.domain;

import cn.catguild.common.entity.jpa.AbstractEntity;
import cn.catguild.common.type.ActiveStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * 租户（以租户为界隔离数据及权限）
 *
 * @author xiyan
 * @date 2023/7/31 15:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_tenant`")
public class Tenant extends AbstractEntity {

    @Comment("租户名")
    private String name;

    @Comment("唯一UID")
    private Integer uid;

    @Comment("注册邮箱")
    private String email;

    @Comment("备注")
    private String remarks;

    @Comment("活跃状态")
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

    @Comment("绑定的域名，多个逗号风格")
    private String domainName;

}
