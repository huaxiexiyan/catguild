package cn.catguild.system.application;

import cn.catguild.common.type.CatTreeNode;
import cn.catguild.system.domain.Menu;
import cn.catguild.system.domain.repositroy.MenuRepository;
import cn.catguild.system.infrastructure.adapter.external.client.PermissionsClient;
import cn.catguild.system.infrastructure.adapter.external.client.dto.AuthResourceDTO;
import cn.catguild.system.infrastructure.domain.type.MenuType;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
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
    private final PermissionsClient permissionsClient;

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

    /**
     * 获取授权菜单树
     *
     * @return
     */
    public List<Menu> getAuthMenuTree(Long appId) {
        // 获取资源授权
        List<AuthResourceDTO> permissions = permissionsClient.getAuthResourceByType(appId, Menu.class.getSimpleName());
        List<Long> resourceIds = permissions.stream().map(AuthResourceDTO::getResourceId).toList();
        if (CollectionUtils.isEmpty(resourceIds)){
            return new ArrayList<>();
        }
        // 获取有权限的菜单
        List<Menu> menus = menuRepository.findByIds(resourceIds, MenuType.MENU);
        if (CollectionUtils.isEmpty(menus)){
            return new ArrayList<>();
        }
        //todo 菜单自定义
        return CatTreeNode.merge(menus);
    }

}
