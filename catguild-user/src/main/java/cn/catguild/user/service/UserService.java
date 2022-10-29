package cn.catguild.user.service;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.domain.vo.CatUserQuery;

import java.util.List;

/**
 * 用户服务
 *
 * @author xiyan
 * @date 2022/9/30 15:19
 */
public interface UserService {

	void add(CatUser catUser);

	void remove(Long id);

	CatUser get(Long id);

	List<CatUser> list(CatUserQuery query);

	ApiPage<CatUser> page(CatUserQuery query);

}
