package cn.catguild.auth.infrastructure.repository.domain.relation;

import cn.catguild.common.entity.jpa.AbstractEntity;
import cn.catguild.common.type.ActiveStatus;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 * 租户app关联关系表
 * </p>
 *
 * @author xiyan
 * @since 2023-10-27
 */
@Getter
@Setter
@TableName("auth_tenant_app")
public class TenantAppDO  extends AbstractEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 租户id
     */
    private Long tenantId;

    /**
     * 来源appId
     */
    private Long appId;

    private ActiveStatus activeStatus;

}
