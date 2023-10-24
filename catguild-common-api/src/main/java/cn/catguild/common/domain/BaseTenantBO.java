package cn.catguild.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/7/31 17:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseTenantBO extends BaseBO{

    /**
     * 租户id
     */
    @JsonIgnore
    private Long tenantId;

}
