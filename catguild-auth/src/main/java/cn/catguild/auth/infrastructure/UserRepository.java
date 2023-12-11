package cn.catguild.auth.infrastructure;

import cn.catguild.auth.domain.CatUser;
import cn.catguild.auth.presentation.model.UserQuery;
import cn.catguild.common.api.ApiPage;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/7/31 15:29
 */
@Repository
public interface UserRepository {


    ApiPage<CatUser> page(UserQuery query);

    void save(CatUser user);

    CatUser findById(Long userId);

}
