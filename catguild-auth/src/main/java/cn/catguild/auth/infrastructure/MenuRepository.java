package cn.catguild.auth.infrastructure;

import cn.catguild.auth.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/8/8 17:20
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {

}
