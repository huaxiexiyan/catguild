package cn.catguild.business.automatic.domain;

import cn.catguild.common.domain.BaseTenantBO;
import cn.catguild.common.type.ActiveStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 授权类型
 *
 * @author xiyan
 * @date 2023/10/20 14:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppAuthType extends BaseTenantBO {

    private String name;

    /**
     * code码预设分配，不可修改
     */
    //@TableField(value = "read_only_field", updateStrategy = FieldStrategy.IGNORE)
    private String code;

    private ActiveStatus activeStatus;

    private LocalDateTime cTime;

}
