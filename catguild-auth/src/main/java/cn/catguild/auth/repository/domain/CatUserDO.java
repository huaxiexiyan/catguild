package cn.catguild.auth.repository.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 系统用户基类
 * 委托人
 *
 *
 * @author xiyan
 * @date 2023/5/1 11:24
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CatUserDO extends BaseGuildDO{

    public Long catUserId;

    public Long guildId;

}
