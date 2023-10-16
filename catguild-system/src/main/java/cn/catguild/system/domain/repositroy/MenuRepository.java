package cn.catguild.system.domain.repositroy;

import cn.catguild.system.domain.Menu;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/13 14:05
 */
public interface MenuRepository {
    void save(Menu menu);

    List<Menu> findAll();

    Menu findById(Long id);

}
