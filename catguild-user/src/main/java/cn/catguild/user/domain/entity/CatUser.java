package cn.catguild.user.domain.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.List;

/**
 * 用户是唯一主体（映射现实的行为人）
 *
 * @author xiyan
 * @date 2022/9/30 15:01
 */
@Data
public class CatUser {

	/**
	 * 主键id（唯一标识，其他都是附属内容）
	 */
	@TableId
	private Long id;

	/**
	 * 一个行为人可以被授予多种身份(特定行为权限)
	 */
	@TableField(exist = false)
	private List<Identity>  identities;

	/**
	 * 一个行为人，只能有一个账号
	 */
	private Long accountId;

	private String name;

}
