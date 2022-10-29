package cn.catguild.user.service;

import cn.catguild.user.domain.entity.Account;

/**
 * @author xiyan
 * @date 2022/10/14 15:10
 */
public interface AccountService {
	void register(Account account);

	Account get(Account account);

	Account login(String username, String password);
}
