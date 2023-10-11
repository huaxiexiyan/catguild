package cn.catguild.business.erp.infrastructure.repository.jpa;

import cn.catguild.business.erp.infrastructure.domain.OnlineOrderDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/10/10 21:23
 */
@Repository
public interface OnlineOrderDORepository extends JpaRepository<OnlineOrderDO, Long> {

    OnlineOrderDO findByTenantIdAndOrderNum(Long tenantId, String orderNum);

}
