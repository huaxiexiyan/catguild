package cn.catguild.business.erp.application;

import cn.catguild.business.erp.domain.OnlineOrderRepository;
import cn.catguild.business.erp.infrastructure.domain.OnlineOrderDO;
import cn.catguild.business.erp.infrastructure.domain.query.OnlineOrderQuery;
import cn.catguild.common.api.ApiPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiyan
 * @date 2023/10/10 21:25
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class OnlineOrderApplication {


    private final OnlineOrderRepository onlineOrderRepository;


    public void save(Long tenantId,OnlineOrderDO onlineOrder) {
        onlineOrderRepository.save(tenantId, onlineOrder);
    }

    public ApiPage<OnlineOrderDO>  page(Long tenantId, OnlineOrderQuery query){
        ApiPage<OnlineOrderDO> page = onlineOrderRepository.page(tenantId,query);
        return page;
    }

}
