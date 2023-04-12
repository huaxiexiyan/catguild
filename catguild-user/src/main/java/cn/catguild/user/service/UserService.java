package cn.catguild.user.service;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.domain.query.CatUserQuery;

import java.util.List;

/**
 * 用户服务
 *
 * @author xiyan
 * @date 2022/9/30 15:19
 */
public interface UserService {

	/**
	 * 新增用户
	 *
	 * @param catuser 用户基本信息
	 * @return 创建后的用户信息
	 */
	CatUser add(Long guildId, CatUser catuser);

	/**
	 *
	 * @param guildId
	 * @param id
	 * @return
	 */
	CatUser get(Long guildId, Long id);

	/**
	 * 根据多种条件查询list
	 *
	 * @param query
	 * @return
	 */
	List<CatUser> list(Long guildId, CatUserQuery query);

	/**
	 * 分页查询条件
	 *
	 * @param apiPage 分页参数
	 * @param query   查询条件
	 * @return 分页对象
	 */
	ApiPage<CatUser> page(Long guildId, ApiPage<CatUser> apiPage, CatUserQuery query);

}
