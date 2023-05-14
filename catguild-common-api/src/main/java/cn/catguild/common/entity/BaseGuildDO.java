package cn.catguild.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/5/14 22:57
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseGuildDO extends BaseDO{

    private Long guildId;

}
