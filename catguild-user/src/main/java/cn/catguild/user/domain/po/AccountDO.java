package cn.catguild.user.domain.po;

import cn.catguild.user.domain.type.AccountStatus;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 账号（系统的登录凭证，并只用作凭证）
 * 一个账号只能被授予给一个人、或一种身份
 *
 * @author xiyan
 * @date 2022/9/30 15:04
 */
@Data
@TableName("account")
public class AccountDO extends BaseGuildDO{

	/**
	 * 登录名
	 */
	private String username;

	/**
	 * 登录密码
	 */
	private String password;

	private Long cat_user_id;

	private AccountStatus status;

}
