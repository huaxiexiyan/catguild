package cn.catguild.business.erp.domain;

import cn.catguild.business.erp.infrastructure.domain.OnlineOrderDO;

/**
 * @author xiyan
 * @date 2023/10/10 21:23
 */
public interface OnlineOrderRepository{
    void save(Long tenantId, OnlineOrderDO onlineOrder);

}
