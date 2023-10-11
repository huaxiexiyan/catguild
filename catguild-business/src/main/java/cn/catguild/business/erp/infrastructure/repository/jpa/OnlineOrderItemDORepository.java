package cn.catguild.business.erp.infrastructure.repository.jpa;

import cn.catguild.business.erp.infrastructure.domain.OnlineOrderItemDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/10 21:23
 */
@Repository
public interface OnlineOrderItemDORepository extends JpaRepository<OnlineOrderItemDO, Long> {

    List<OnlineOrderItemDO> findByTenantIdAndOrderId(Long tenantId,Long orderId);

}
