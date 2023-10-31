package cn.catguild.auth.presentation.model;

import cn.catguild.auth.domain.Resource;
import cn.catguild.common.entity.BaseQuery;
import cn.catguild.common.type.ActiveStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/8/22 15:43
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ResourceQuery extends BaseQuery<Resource> {

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

    private ActiveStatus activeStatus;

}
