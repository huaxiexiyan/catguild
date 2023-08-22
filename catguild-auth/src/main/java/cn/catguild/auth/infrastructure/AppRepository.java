package cn.catguild.auth.infrastructure;

import cn.catguild.auth.domain.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/7/31 17:51
 */
@Repository
public interface AppRepository extends JpaRepository<App,Long> {
}
