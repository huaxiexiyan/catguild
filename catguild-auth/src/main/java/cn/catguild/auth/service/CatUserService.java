package cn.catguild.auth.service;

import cn.catguild.auth.domain.CatUser;
import cn.catguild.auth.domain.type.GuildId;

/**
 * @author xiyan
 * @date 2023/5/1 11:36
 */
public interface CatUserService {

    /**
     * 通过账号登录凭证名获取公会长信息
     *
     * @param guild
     * @param username
     * @return
     */
    CatUser findGuildMasterByAccountName(GuildId guild, String username);

}
