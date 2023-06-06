package cn.catguild.auth.oauth.repositroy;

import cn.catguild.auth.oauth.domain.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/6/6 15:58
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, String> {

    Optional<Client> findByClientId(String clientId);

}
