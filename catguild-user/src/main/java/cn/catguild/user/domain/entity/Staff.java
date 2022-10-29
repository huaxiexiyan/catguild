package cn.catguild.user.domain.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 职员（公会的工作人员（会长作为特殊工作人员））
 * 作为一种人的身份（其身份中绑定了对应的权限）
 *
 * @author xiyan
 * @date 2022/9/30 15:07
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Staff extends Identity{

	/**
	 * 职员姓名
	 */
	private String name;
	/**
	 * 年龄
	 */
	private String age;
	/**
	 * 性别
	 */
	private String sex;

}
