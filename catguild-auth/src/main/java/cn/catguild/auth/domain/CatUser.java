package cn.catguild.auth.domain;

import cn.catguild.auth.domain.type.CatUserId;
import cn.catguild.auth.domain.type.GuildId;
import lombok.Data;

/**
 * 系统用户基类
 * 委托人
 *
 *
 * @author xiyan
 * @date 2023/5/1 11:24
 */
@Data
public abstract class CatUser {

    public CatUserId catUserId;

    public GuildId guildId;

    public CatAccount catAccount;


}
