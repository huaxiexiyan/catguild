package cn.catguild.user.service;

import cn.catguild.user.domain.bo.AuthUser;
import cn.catguild.user.domain.entity.Account;

/**
 * 用户服务
 *
 * @author xiyan
 * @date 2022/9/30 15:19
 */
public interface AuthService {

	/**
	 * 用户登录
	 *
	 * @param guildId
	 * @param appType
	 * @param account
	 * @return
	 */
	AuthUser login(Long guildId, Integer appType, Account account);

	/**
	 * 账号与用户绑定
	 *
	 * @param guildId
	 * @param catUserId
	 * @param accountId
	 * @return
	 */
	boolean binding(Long guildId, Long catUserId, Long accountId);

	/**
	 * 解绑账号用户解绑
	 *
	 * @param guildId
	 * @param catUserId
	 * @param accountId
	 * @return
	 */
	boolean unbinding(Long guildId, Long catUserId, Long accountId);


}
