package cn.catguild.user.service.repository;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.domain.vo.CatUserQuery;

import java.util.List;

/**
 * @author xiyan
 * @date 2022/9/30 15:55
 */
public interface UserRepository {


	void save(CatUser catUser);

	void remove(Long id);

	CatUser get(Long id);

	List<CatUser> list(CatUserQuery query);

	ApiPage<CatUser> page(CatUserQuery query);

}
