package cn.catguild.auth.service;

import cn.catguild.auth.controller.command.GuildCommand;
import cn.catguild.auth.domain.Guild;
import cn.catguild.auth.domain.type.GuildId;
import cn.catguild.common.api.ApiPage;

/**
 * 公会资源服务接口
 *
 * @author xiyan
 * @date 2023/5/13 11:46
 */
public interface GuildService {
    ApiPage<Guild> getPage(GuildCommand guildCommand);

    Guild get(GuildId id);

    void add(Guild guild);

    void update(Guild guild);

    void updateByStatus(Long id,GuildCommand guildCommand);

}
