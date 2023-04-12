package cn.catguild.user.service.repository;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.domain.query.CatUserQuery;

import java.util.List;

/**
 * @author xiyan
 * @date 2022/9/30 15:55
 */
public interface UserRepository {

	CatUser save(Long guildId, CatUser catUser);

	void remove(Long guildId, Long id);

	CatUser find(Long guildId, Long id);

	List<CatUser> list(Long guildId, CatUserQuery query);

	ApiPage<CatUser> page(Long guildId, ApiPage<CatUser> apiPage, CatUserQuery query);

}
