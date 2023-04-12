package cn.catguild.user.repository;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.query.AccountQuery;
import cn.catguild.user.repository.mapper.AccountMapper;
import cn.catguild.user.service.repository.AccountRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

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
	public Account save(Account account) {
		return null;
	}

	@Override
	public Account remove(Long id) {
		return null;
	}

	@Override
	public Account find(Long id) {
		return null;
	}

	@Override
	public List<Account> list(AccountQuery query) {
		return null;
	}

	@Override
	public ApiPage<Account> page(ApiPage<Account> apiPage, AccountQuery query) {
		return null;
	}

}
