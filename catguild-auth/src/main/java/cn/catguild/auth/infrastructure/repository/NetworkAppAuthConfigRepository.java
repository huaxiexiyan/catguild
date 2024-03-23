package cn.catguild.auth.infrastructure.repository;

import cn.catguild.auth.domain.NetworkAppAuthConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/9/26 11:24
 */
@Repository
public interface NetworkAppAuthConfigRepository extends JpaRepository<NetworkAppAuthConfig,Long> {

}
