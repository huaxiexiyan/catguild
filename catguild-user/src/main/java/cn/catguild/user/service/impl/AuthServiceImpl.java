package cn.catguild.user.service.impl;

import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.service.AuthService;
import cn.catguild.user.service.repository.AccountRepository;
import cn.catguild.user.service.repository.UserRepository;
import cn.catguild.user.utility.BusinessCheckUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 *
 * @author xiyan
 * @date 2022/9/30 15:54
 */
@AllArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	private final UserRepository userRepository;
	private final AccountRepository accountRepository;

	@Override
	public void login(Integer appType, Account account) {
		// 1、判断账号名与密码是否正确
		Account targetAccount = accountRepository.getByUsername(account.getUsername());
		BusinessCheckUtils.checkBoolean(
			targetAccount == null || !targetAccount.getPassword().equals(account.getPassword()),
			"账号名或密码不正确");
		// 2、判断该用户是在当前系统注册过
		CatUser targetUser = userRepository.getByAccountId(targetAccount.getId());
		//BusinessCheckUtils.checkBoolean(
		//	targetUser.getIdentities().contains(appType),
		//	"当前账号未在此系统登录过");
		// 3、查询改用户的权限菜单
	}

}
