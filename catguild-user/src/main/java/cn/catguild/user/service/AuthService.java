package cn.catguild.user.service;

import cn.catguild.user.domain.entity.Account;

/**
 * 用户服务
 *
 * @author xiyan
 * @date 2022/9/30 15:19
 */
public interface AuthService {

	void login(Integer appType, Account account);

}
