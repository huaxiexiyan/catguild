package cn.catguild.user.service.impl;

import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.service.AccountService;
import cn.catguild.user.service.repository.AccountRepository;
import cn.catguild.user.service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author xiyan
 * @date 2022-08-04 17:38
 */
@AllArgsConstructor
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;
	private final UserRepository userService;

	@Override
	public void register(Account account) {
		// 注册用户-先创建账号，然后创建用户，并把账号与用户绑定
		accountRepository.save(account);
		CatUser catUser = new CatUser();
		catUser.setAccountId(account.getId());
		catUser.setName(account.getUsername());
		userService.save(catUser);
	}

	@Override
	public Account get(Account account) {
		return null;
	}

	@Override
	public Account login(String username, String password) {
		return null;
	}

}
