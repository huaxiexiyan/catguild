package cn.catguild.system.infrastructure.repository.jpa;

import cn.catguild.system.infrastructure.domain.MenuDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/10/13 14:08
 */
@Repository
public interface MenuDORepository extends JpaRepository<MenuDO, Long> {
}
