package cn.catguild.auth.domain;

import cn.catguild.common.domain.BaseTenantBO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/8/22 15:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Resource extends BaseTenantBO {

    /**
     * 实体id
     */
    private Long refId;

    /**
     * 类型
     */
    private String refType;

    /**
     * 什么应用产生的
     */
    private Long appId;

}
