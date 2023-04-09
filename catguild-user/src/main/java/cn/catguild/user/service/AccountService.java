package cn.catguild.user.service;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.query.AccountQuery;

import java.util.List;

/**
 * @author xiyan
 * @date 2022/10/14 15:10
 */
public interface AccountService {

	/**
	 * 新增账号
	 *
	 * @param account 账号基本信息
	 * @return 创建后的账号信息
	 */
	Account add(Account account);

	/**
	 * 检查账号是否存在
	 *
	 * @param username 登录凭证名
	 * @return 如果存在，返回该账号信息，否则返回空
	 */
	Account check(String username);

	/**
	 * 冻结账号
	 *
	 * @param accountId 账号id
	 * @return 成功返回true，否则返回false
	 */
	boolean frozen(Long accountId);

	/**
	 * 根据单一条件查询账号
	 *
	 * @param query
	 * @return
	 */
	Account get(AccountQuery query);

	/**
	 * 根据多种条件查询list
	 *
	 * @param query
	 * @return
	 */
	List<Account> list(AccountQuery query);

	/**
	 * 分页查询条件
	 *
	 * @param apiPage 分页参数
	 * @param query 查询条件
	 * @return 分页对象
	 */
	ApiPage<Account> page(ApiPage<Account> apiPage,AccountQuery query);

}
