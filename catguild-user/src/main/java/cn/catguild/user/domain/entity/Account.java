package cn.catguild.user.domain.entity;

import cn.catguild.common.entity.BaseEntity;
import lombok.*;

/**
 * 账号-登录凭证
 *
 * @author xiyan
 * @date 2022-04-04 13:24
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Account extends BaseEntity {

	/**
	 * 账户名（唯一）
	 **/
	private String username;
	/**
	 * 密码
	 **/
	private String password;
	/**
	 * 绑定手机（唯一）
	 **/
	private String telephone;
	/**
	 * 绑定邮箱（唯一）
	 **/
	private String email;

}
