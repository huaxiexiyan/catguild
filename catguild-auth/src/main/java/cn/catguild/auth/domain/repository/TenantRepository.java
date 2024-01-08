package cn.catguild.auth.domain.repository;

import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.infrastructure.repository.domain.query.TenantQuery;
import cn.catguild.common.api.ApiPage;
import org.springframework.data.repository.query.Param;

/**
 * @author xiyan
 * @date 2023/7/31 17:51
 */
public interface TenantRepository  {
    Tenant findByDomainName(@Param("domainName") String domainName);

    void save(Tenant tenant);

    Tenant findById(Long id);

    ApiPage<Tenant> page(TenantQuery query);

}
