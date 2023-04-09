package cn.catguild.user.service.impl;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.query.AccountQuery;
import cn.catguild.user.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xiyan
 * @date 2022-08-04 17:38
 */
@AllArgsConstructor
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {


	@Override
	public Account add(Long guildId, Account account) {
		return null;
	}

	@Override
	public Account check(Long guildId, String username) {
		return null;
	}

	@Override
	public boolean frozen(Long guildId, Long accountId) {
		return false;
	}

	@Override
	public boolean updatePassword(Long guildId, Long accountId, String newPassword) {
		return false;
	}

	@Override
	public Account get(Long guildId, AccountQuery query) {
		return null;
	}

	@Override
	public List<Account> list(Long guildId, AccountQuery query) {
		return null;
	}

	@Override
	public ApiPage<Account> page(Long guildId, ApiPage<Account> apiPage, AccountQuery query) {
		return null;
	}
}
