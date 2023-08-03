package cn.catguild.auth.infrastructure;

import cn.catguild.auth.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/7/31 17:51
 */
@Repository
public interface TenantRepository extends JpaRepository<Tenant,Long> {
}