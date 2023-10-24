package cn.catguild.system.domain.repositroy;

import cn.catguild.common.api.ApiPage;
import cn.catguild.system.domain.App;
import cn.catguild.system.infrastructure.domain.query.AppQuery;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/22 17:19
 */
public interface AppRepository {

    void save(App app);

    List<App> findAll();

    App findById(Long id);


    ApiPage<App> page(AppQuery query);

    List<App> findByParentId(Long parentId);

}
