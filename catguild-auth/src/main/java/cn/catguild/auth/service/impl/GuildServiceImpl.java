package cn.catguild.auth.service.impl;

import cn.catguild.auth.controller.command.GuildCommand;
import cn.catguild.auth.domain.Guild;
import cn.catguild.auth.domain.type.GuildId;
import cn.catguild.auth.service.GuildService;
import cn.catguild.auth.service.repository.GuildRepository;
import cn.catguild.common.api.ApiPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 公会资源服务接口 实现类
 *
 * @author xiyan
 * @date 2023/5/13 11:46
 */
@Slf4j
@AllArgsConstructor
@Service
public class GuildServiceImpl implements GuildService {

  private GuildRepository guildRepository;

  @Override
  public ApiPage<Guild> getPage(GuildCommand guildCommand) {
    return guildRepository.getPage(guildCommand);
  }

  @Override
  public Guild get(GuildId id) {
    return guildRepository.get(id);
  }

  @Override
  public void add(Guild guild) {
    guildRepository.save(guild);
  }

  @Override
  public void update(Guild guild) {
    guildRepository.save(guild);
  }

  @Override
  public void updateByStatus(Long id, GuildCommand guildCommand) {
    Guild guild = new Guild();
    guild.setId(new GuildId(id));
    guild.setStatus(guildCommand.getStatus());
    guildRepository.save(guild);
  }
}
