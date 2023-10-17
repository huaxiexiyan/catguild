package cn.catguild.system.application;

import cn.catguild.common.type.CatTreeNode;
import cn.catguild.common.utility.JSONUtils;
import cn.catguild.system.domain.Menu;
import cn.catguild.system.domain.repositroy.MenuRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/8 17:21
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class MenuApplication {

    private final MenuRepository menuRepository;

    /**
     * 新增系统菜单
     *
     * @param menu
     */
    public void addMenu(Menu menu) {
        menuRepository.save(menu);
    }

    /**
     * 获取菜单树
     *
     * @return
     */
    public List<Menu> tree() {
        List<Menu> menus = menuRepository.findAll();
        List<Menu> merge = CatTreeNode.merge(menus);
        JSONUtils.toJsonStr(merge);
        return CatTreeNode.merge(menus);
    }


    public void updateMenu(Long id, Menu menu) {
        menu.setId(id);
        menuRepository.save(menu);
    }

    public void patchStatus(Long id, Menu menu) {
        menu.setId(id);
        menuRepository.save(menu);
    }

    public Menu getById(Long id) {
        return menuRepository.findById(id);
    }
}
