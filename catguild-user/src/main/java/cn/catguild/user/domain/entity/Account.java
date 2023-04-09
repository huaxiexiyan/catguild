package cn.catguild.user.domain.entity;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 账号（系统的登录凭证，并只用作凭证）
 * 一个账号只能被授予给一个人、或一种身份
 *
 * @author xiyan
 * @date 2022/9/30 15:04
 */
@Data
public class Account {

	private Integer id;

	/**
	 * 登录名
	 */
	private Integer guildId;

	/**
	 * 登录名
	 */
	private String username;

	/**
	 * 登录密码
	 */
	private String password;

	private LocalDateTime createdTime;

}
