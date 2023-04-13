package cn.catguild.user.service.impl;

import cn.catguild.user.domain.bo.AuthUser;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.domain.query.AccountQuery;
import cn.catguild.user.exception.BizException;
import cn.catguild.user.service.AccountService;
import cn.catguild.user.service.AuthService;
import cn.catguild.user.service.CatUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;

import java.nio.charset.StandardCharsets;

/**
 * @author xiyan
 * @date 2022/9/30 15:54
 */
@AllArgsConstructor
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

	private final AccountService accountService;
	private final CatUserService catUserService;


	@Override
	public AuthUser login(Long guildId, Integer appType, AccountQuery accountQuery) {
		/**
		 * 一个账号对应唯一的一个用户（）
		 * **/
		Account loginAccount = accountService.check(guildId, accountQuery.getUsername());
		if (loginAccount == null) {
			throw new BizException("账号不存在");
		}
		if (!StringUtils.pathEquals(loginAccount.getPassword(), accountQuery.getPassword())){
			throw new BizException("账号或密码错误");
		}
		CatUser catUser = catUserService.get(guildId, loginAccount.getCatUserId());
		AuthUser authUser = new AuthUser();
		authUser.setCatUserId(catUser.getId());
		authUser.setName(catUser.getName());
		authUser.setToken(Base64Utils.encodeToString((catUser.getId()+"@"+catUser.getName()).getBytes(StandardCharsets.UTF_16)));
		return authUser;
	}

	@Override
	public boolean binding(Long guildId, Long catUserId, Long accountId) {
		return false;
	}

	@Override
	public boolean unbinding(Long guildId, Long catUserId, Long accountId) {
		return false;
	}

}
