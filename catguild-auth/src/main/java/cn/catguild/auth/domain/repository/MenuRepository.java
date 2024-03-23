package cn.catguild.auth.domain.repository;

import cn.catguild.auth.domain.Menu;
import cn.catguild.auth.infrastructure.repository.domain.query.MenuQuery;
import cn.catguild.common.api.ApiPage;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/8 17:20
 */
@Repository
public interface MenuRepository  {

	ApiPage<Menu> page(MenuQuery menuQuery);

	List<Menu> list(MenuQuery menuQuery);

}
