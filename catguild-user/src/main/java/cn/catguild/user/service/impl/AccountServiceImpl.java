package cn.catguild.user.service.impl;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.query.AccountQuery;
import cn.catguild.user.service.AccountService;
import cn.catguild.user.service.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author xiyan
 * @date 2022-08-04 17:38
 */
@AllArgsConstructor
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	@Override
	public Account add(Long guildId, Account account) {
		return accountRepository.save(account);
	}

	@Override
	public Account check(Long guildId, String username) {
		AccountQuery query = new AccountQuery();
		List<Account> list = accountRepository.list(query);
		return CollectionUtils.isEmpty(list) ? null : list.get(0);
	}

	@Override
	public void frozen(Long guildId, Long id) {
		Account account = accountRepository.find(id);
		account.frozen();
		accountRepository.save(account);
	}

	@Override
	public boolean updatePassword(Long guildId, Long id, String newPassword) {
		return false;
	}

	@Override
	public Account get(Long guildId, Long id) {
		return accountRepository.find(id);
	}

	@Override
	public List<Account> list(Long guildId, AccountQuery query) {
		return accountRepository.list(query);
	}

	@Override
	public ApiPage<Account> page(Long guildId, ApiPage<Account> apiPage, AccountQuery query) {
		return accountRepository.page(apiPage, query);
	}
}
