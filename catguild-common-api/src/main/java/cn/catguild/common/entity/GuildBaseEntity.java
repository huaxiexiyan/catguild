package cn.catguild.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2022-04-04 08:59
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GuildBaseEntity extends BaseEntity {

	/**
	 * 公会id
	 */
	private Long guildId;

}
