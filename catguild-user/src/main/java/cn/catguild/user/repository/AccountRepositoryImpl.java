package cn.catguild.user.repository;

import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.repository.mapper.AccountMapper;
import cn.catguild.user.service.repository.AccountRepository;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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
	public void save(Account account) {
		accountMapper.insert(account);
	}

	@Override
	public Account getByUsername(String username) {
		return accountMapper.selectOne(Wrappers.<Account>lambdaQuery().eq(Account::getUsername,username));
	}

}
