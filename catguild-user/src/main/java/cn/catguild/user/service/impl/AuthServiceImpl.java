package cn.catguild.user.service.impl;

import cn.catguild.user.domain.bo.AuthUser;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.service.AuthService;
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


	@Override
	public AuthUser login(Long guildId, Integer appType, Account account) {
		return null;
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
