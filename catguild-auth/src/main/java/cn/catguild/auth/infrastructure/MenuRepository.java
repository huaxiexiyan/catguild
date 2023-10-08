package cn.catguild.auth.infrastructure;

import cn.catguild.auth.domain.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/8 17:20
 */
@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    @Query("select m from Menu m join Resource r on r.refId = m.id " +
            "where r.id in (:resourceIds)")
    List<Menu> findByResourceIds(@Param("resourceIds") List<Long> resourceIds);

}
