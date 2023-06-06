//package cn.catguild.auth.repository;
//
//import cn.catguild.auth.controller.command.GuildCommand;
//import cn.catguild.auth.domain.Guild;
//import cn.catguild.auth.domain.type.GuildId;
//import cn.catguild.auth.repository.domain.GuildDO;
//import cn.catguild.auth.repository.mapper.GuildMapper;
//import cn.catguild.auth.service.repository.GuildRepository;
//import cn.catguild.common.api.ApiPage;
//import cn.catguild.common.utility.IPageUtils;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.core.toolkit.Wrappers;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import java.util.ArrayList;
//import java.util.List;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.stereotype.Repository;
//
///**
// * 公会资源仓储实现类
// *
// * @author xiyan
// * @date 2023/5/13 11:49
// */
//@AllArgsConstructor
//@Slf4j
//@Repository
//public class GuildRepositoryImpl implements GuildRepository {
//
//  private GuildMapper guildMapper;
//
//  @Override
//  public void save(Guild guild) {
//    GuildDO guildDO = toDO(guild);
//    if (guild.getId() == null) {
//      // 创建
//      guildMapper.insert(guildDO);
//    } else {
//      // 更新
//      guildMapper.updateById(guildDO);
//    }
//  }
//
//  @Override
//  public Guild get(GuildId id) {
//    GuildDO guildDO = guildMapper.selectById(id.getValue());
//    return toEntity(guildDO);
//  }
//
//  @Override
//  public ApiPage<Guild> getPage(GuildCommand guildCommand) {
//    GuildDO guildDO = new GuildDO();
//    IPage<GuildDO> iPage =
//        guildMapper.selectPage(
//            IPageUtils.toIPage(new Page<>(), guildCommand),
//            Wrappers.<GuildDO>lambdaQuery().setEntity(guildDO));
//    return IPageUtils.toApiPage(
//        iPage,
//        (page) -> {
//          // 执行自定义的类型转换逻辑
//          List<Guild> convertedList = new ArrayList<>();
//          for (Object record : page.getRecords()) {
//            // 执行类型转换
//            convertedList.add(toEntity((GuildDO) record));
//          }
//          return convertedList;
//        });
//  }
//
//  private GuildDO toDO(Guild guild) {
//    GuildDO guildDO = new GuildDO();
//    BeanUtils.copyProperties(guild, guildDO);
//    guildDO.setId(guild.getId().getValue());
//    return guildDO;
//  }
//
//  private Guild toEntity(GuildDO guildDO) {
//    Guild guild = new Guild();
//    BeanUtils.copyProperties(guildDO, guild);
//    guild.setId(new GuildId(guildDO.getId()));
//    return guild;
//  }
//
//}
