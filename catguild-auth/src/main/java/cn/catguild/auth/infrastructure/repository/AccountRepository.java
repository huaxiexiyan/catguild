package cn.catguild.auth.infrastructure.repository;

import cn.catguild.auth.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/7/31 15:34
 */
@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    Account findByUsername(String username);

    Account findByTenantIdAndUsername(Long TenantId,String username);

}
