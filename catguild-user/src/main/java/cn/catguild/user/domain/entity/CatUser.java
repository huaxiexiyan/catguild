package cn.catguild.user.domain.entity;


import cn.catguild.common.entity.GuildBaseEntity;
import cn.catguild.user.domain.type.CatUserStatus;
import cn.catguild.user.domain.type.Sex;
import cn.hutool.core.text.CharSequenceUtil;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 用户实体
 *
 * @author xiyan
 * @date 2022-07-23 17:16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CatUser extends GuildBaseEntity {

	/**
	 * 匿名账号-和用户绑定的唯一账号
	 **/
	private Long accountId;

	private String name;

	private Sex sex;

	private Integer age;

	/**
	 * 人物等级
	 **/
	private Integer level;

	private String address;

	private String idCard;

	private CatUserStatus status;

	private LocalDateTime registerTime;

	public void generateName() {
		if (CharSequenceUtil.isNotBlank(this.name)) {
			this.name = "cat_" + UUID.randomUUID();
		}
	}
}
