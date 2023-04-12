package cn.catguild.user.domain.entity;

import cn.catguild.user.domain.po.AccountDO;
import cn.catguild.user.domain.type.AccountStatus;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 账号（系统的登录凭证，并只用作凭证）
 * 一个账号只能被授予给一个人、或一种身份
 *
 * @author xiyan
 * @date 2022/9/30 15:04
 */
@Getter
public class Account {

	private Long id;

	/**
	 *
	 */
	private Long guildId;

	/**
	 * 登录名
	 */
	private String username;

	/**
	 * 登录密码
	 */
	private String password;

	private AccountStatus status;

	private LocalDateTime createdTime;

	public Account(AccountDO accountDO){
		this.id = accountDO.getId();
		this.guildId = accountDO.getGuildId();
		this.username = accountDO.getUsername();
		this.status = accountDO.getStatus();
		this.createdTime = accountDO.getCreatedTime();
	}

	/**
	 * 冻结
	 */
	public void frozen() {
		status = AccountStatus.FROZEN;
	}

	public void thaw() {
		status = AccountStatus.NORMAL;
	}

	public void updatePassword(String newPassword) {
		password = newPassword;
	}

}
