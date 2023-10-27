package cn.catguild.auth.infrastructure.repository.domain.entity;

import cn.catguild.common.entity.jpa.AbstractEntity;
import cn.catguild.common.type.ActiveStatus;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 角色资源关联关系表
 * </p>
 *
 * @author xiyan
 * @since 2023-10-27
 */
@Getter
@Setter
@TableName("auth_tenant")
public class TenantDO extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 唯一UID
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Integer uid;

    /**
     * 租户名
     */
    private String name;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remarks;

    /**
     *  活跃状态
     */
    private ActiveStatus activeStatus;

    /**
     * 绑定的域名，多个逗号风格
     */
    private String domainName;

}
