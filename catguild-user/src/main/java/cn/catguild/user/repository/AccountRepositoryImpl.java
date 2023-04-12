package cn.catguild.user.repository;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.po.AccountDO;
import cn.catguild.user.domain.query.AccountQuery;
import cn.catguild.user.repository.mapper.AccountMapper;
import cn.catguild.user.service.repository.AccountRepository;
import cn.catguild.user.utility.IPageUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2022/10/14 15:15
 */
@Slf4j
@AllArgsConstructor
@Repository
public class AccountRepositoryImpl implements AccountRepository {

	private final AccountMapper accountMapper;

	@Override
	public Account save(Long guildId, Account account) {
		AccountDO accountDO = new AccountDO();
		BeanUtils.copyProperties(account, accountDO);
		accountDO.setGuildId(guildId);
		accountMapper.insert(accountDO);
		return account;
	}

	@Override
	public void remove(Long guildId, Long id) {
		accountMapper.delete(Wrappers.<AccountDO>lambdaQuery()
			.eq(AccountDO::getGuildId, guildId)
			.eq(AccountDO::getId, id));
	}

	@Override
	public Account find(Long guildId, Long id) {
		AccountDO accountDO = accountMapper.selectOne(Wrappers.<AccountDO>lambdaQuery()
			.eq(AccountDO::getGuildId, guildId)
			.eq(AccountDO::getId, id));
		return new Account(accountDO);
	}

	@Override
	public List<Account> list(Long guildId, AccountQuery query) {
		return accountMapper.selectList(Wrappers.<AccountDO>lambdaQuery()
				.eq(AccountDO::getUsername, query.getUsername()))
			.stream()
			.map(Account::new)
			.collect(Collectors.toList());
	}

	@Override
	public ApiPage<Account> page(Long guildId, ApiPage<Account> apiPage, AccountQuery query) {
		return IPageUtils.toApiPage(new ApiPage<>(),
			accountMapper.selectPage(IPageUtils.toIPage(new Page<>(), apiPage),
				Wrappers.<AccountDO>lambdaQuery()
					.eq(AccountDO::getGuildId, guildId)
					.eq(AccountDO::getUsername, query.getUsername())));
	}

}
