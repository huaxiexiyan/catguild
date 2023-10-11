package cn.catguild.business.erp.infrastructure.repository.jpa;

import cn.catguild.business.erp.infrastructure.domain.OnlineOrderLogisticsDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/10/10 21:23
 */
@Repository
public interface OnlineOrderLogisticsDORepository extends JpaRepository<OnlineOrderLogisticsDO, Long> {

    OnlineOrderLogisticsDO findByTenantIdAndOrderId(Long tenantId, Long orderId);

}
