package cn.catguild.user.service;

import cn.catguild.user.domain.auth.JwtCredentials;
import cn.catguild.user.domain.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author xiyan
 * @date 2022-08-04 17:37
 */
public interface AccountService extends IService<Account> {

	/**
	 * 登陆授权
	 *
	 * @param account
	 * @return
	 */
    JwtCredentials authorization(Account account);

}
