package cn.catguild.auth.application;

import cn.catguild.auth.domain.Menu;
import cn.catguild.auth.infrastructure.MenuRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.common.entity.auth.TokenUser;
import cn.catguild.common.type.CatTreeNode;
import cn.catguild.common.type.auth.UserAuthorityType;
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
public class MenuApplicationService {

    private final IdGenerationClient idClient;

    private MenuRepository menuRepository;

    //private ResourceRepository resourceRepository;

    public void addMenu(Menu menu) {
        // 注册资源
        //resourceRepository.findById(menu.getResourceId())
        //        .ifPresent(resource -> {
        //            // 添加菜单
        //            Long id = idClient.nextId();
        //            menu.setId(id);
        //            menu.setCTime(LocalDateTime.now());
        //            menuRepository.saveAndFlush(menu);
        //
        //            resource.setRefId(id);
        //            resourceRepository.saveAndFlush(resource);
        //        });
    }

    /**
     * 获取菜单树
     * 1、获取当前登录用户信息
     * 2、获取租户的菜单
     * 3、获取租户下该用户的拥有权限的菜单
     * 4、组合菜单树返回
     *
     * @return
     */
    public List<Menu> tree() {
        List<Menu> all = menuRepository.findAll();
        return CatTreeNode.merge(all);
    }

    /**
     * 获取当前app的，当前用户的菜单树
     *
     * @return
     */
    public List<Menu> authTree() {
        TokenUser tokenUser = AuthUtil.getTokenUser();
        if (tokenUser.getAuthorityType() == UserAuthorityType.SUPER_ADMINISTRATOR){
            // 直接返回所有权限树
            return tree();
        }
        return tree();
    }

}
