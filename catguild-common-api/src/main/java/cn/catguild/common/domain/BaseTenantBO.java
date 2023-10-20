package cn.catguild.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * @author xiyan
 * @date 2023/7/31 17:22
 */
@Data
public abstract class BaseTenantBO {

    /**
     * 主键
     */
    private Long id;

    /**
     * 租户id
     */
    @JsonIgnore
    private Long tenantId;

}
