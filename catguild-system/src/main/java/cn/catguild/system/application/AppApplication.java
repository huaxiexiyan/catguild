package cn.catguild.system.application;

import cn.catguild.common.api.ApiPage;
import cn.catguild.system.domain.App;
import cn.catguild.system.domain.repositroy.AppRepository;
import cn.catguild.system.domain.repositroy.MenuRepository;
import cn.catguild.system.infrastructure.domain.query.AppQuery;
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
public class AppApplication {

    private final AppRepository baseRepository;
    private final MenuRepository menuRepository;

    public void addApp(App app) {
        baseRepository.save(app);
    }

    public void updateApp(App app) {
        baseRepository.save(app);
    }

    public void updateAppActiveStatus(Long id) {
        App app = baseRepository.findById(id);
        app.switchActiveStatus();
        baseRepository.save(app);
    }

    public ApiPage<App> page(AppQuery query) {
        return baseRepository.page(query);
    }


    public List<App> tree() {
        return baseRepository.findAll();
    }

    public List<App> listMainApp() {
        return baseRepository.findByParentId(0L);
    }

    public App findById(Long id) {
        return baseRepository.findById(id);
    }

    public void onlyUpdateAppMenu(App app) {
        baseRepository.onlyUpdateAppMenu(app);
    }

}
