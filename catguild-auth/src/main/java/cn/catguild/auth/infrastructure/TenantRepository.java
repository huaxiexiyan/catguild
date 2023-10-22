package cn.catguild.auth.infrastructure;

import cn.catguild.auth.domain.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/7/31 17:51
 */
@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

    @Query("select t from Tenant  t " +
            "where t.domainName = :domainName " +
            "or t.domainName like CONCAT(:domainName, ',%')" +
            "or t.domainName like CONCAT('%,',:domainName, ',%')" +
            "or t.domainName like CONCAT('%,', :domainName)")
    List<Tenant> findByDomainName(@Param("domainName") String domainName);

}
