package cn.catguild.auth.application;

import cn.catguild.auth.domain.Menu;
import cn.catguild.auth.infrastructure.MenuRepository;
import cn.catguild.common.type.CatTreeNode;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/8 17:21
 */
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class MenuApplicationService {

    private MenuRepository menuRepository;

    public List<Menu> tree() {
        List<Menu> all = menuRepository.findAll();
        return CatTreeNode.merge(all);
    }

}
