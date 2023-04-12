package cn.catguild.user.service.repository;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.query.AccountQuery;

import java.util.List;


/**
 * @author xiyan
 * @date 2022/10/14 15:14
 */
public interface AccountRepository {

	Account save(Account account);

	Account remove(Long id);

	Account find(Long id);

	List<Account> list(AccountQuery query);

    ApiPage<Account> page(ApiPage<Account> apiPage, AccountQuery query);

}
