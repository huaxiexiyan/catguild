package cn.catguild.user.domain.entity;

import lombok.Data;

/**
 * @author xiyan
 * @date 2022/9/30 15:12
 */
@Data
public class Identity {

	private Long id;

	/**
	 * 一个具体的身份，也可以拥有唯一一个账号（此账号与用户中的账号不冲突）
	 */
	private Long accountId;

}
