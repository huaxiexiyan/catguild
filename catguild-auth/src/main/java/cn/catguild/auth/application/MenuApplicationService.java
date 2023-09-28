package cn.catguild.auth.application;

import cn.catguild.auth.domain.Menu;
import cn.catguild.auth.infrastructure.MenuRepository;
import cn.catguild.auth.infrastructure.ResourceRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.common.type.CatTreeNode;
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

    private ResourceRepository resourceRepository;

    public void addMenu(Menu menu) {
        // 注册资源
        resourceRepository.findById(menu.getResourceId())
                .ifPresent(resource -> {
                    // 添加菜单
                    Long id = idClient.nextId();
                    menu.setId(id);
                    menuRepository.saveAndFlush(menu);

                    resource.setRefId(id);
                    resourceRepository.saveAndFlush(resource);
                });
    }

    public List<Menu> tree() {
        List<Menu> all = menuRepository.findAll();
        return CatTreeNode.merge(all);
    }


}
