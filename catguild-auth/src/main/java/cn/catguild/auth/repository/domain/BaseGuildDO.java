package cn.catguild.auth.repository.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/5/7 20:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BaseGuildDO extends BaseDO{

    private Long guildId;

}
