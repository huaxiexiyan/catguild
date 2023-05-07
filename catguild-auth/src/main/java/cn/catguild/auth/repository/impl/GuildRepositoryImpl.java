package cn.catguild.auth.repository.impl;

import cn.catguild.auth.domain.Guild;
import cn.catguild.auth.domain.type.GuildId;
import cn.catguild.auth.repository.domain.GuildDO;
import cn.catguild.auth.repository.mapper.GuildMapper;
import cn.catguild.auth.service.repository.GuildRepository;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

/**
 * @author xiyan
 * @date 2023/5/7 20:36
 */
@AllArgsConstructor
@Repository
public class GuildRepositoryImpl implements GuildRepository {

    private GuildMapper guildMapper;

    @Override
    public Guild find(GuildId guildId) {
        GuildDO guildDO = guildMapper.selectById(guildId.getValue());
        return fromData(guildDO);
    }

    @Override
    public void remove(Guild aggregate) {
        GuildDO guildDO = toData(aggregate);
        guildMapper.delete(Wrappers.<GuildDO>emptyWrapper()
                .setEntity(guildDO));
    }

    @Override
    public void save(Guild aggregate) {
        if (aggregate.getId() != null && aggregate.getId().getValue() > 0) {
            // update
            GuildDO guildDO = toData(aggregate);
            guildMapper.updateById(guildDO);
        } else {
            // insert
            GuildDO guildDO = toData(aggregate);
            guildMapper.insert(guildDO);
            aggregate.setId(fromData(guildDO).getId());
        }
    }

    /**
     * DO 转实体
     * @param guildDO
     * @return
     */
    private Guild fromData(GuildDO guildDO) {
        Guild guild = new Guild();
        BeanUtils.copyProperties(guildDO, guild);
        guild.setId(new GuildId(guildDO.getId()));
        return guild;
    }

    /**
     * 实体转 DO
     *
     * @param aggregate
     * @return
     */
    private GuildDO toData(Guild aggregate) {
        GuildDO guildDO = new GuildDO();
        BeanUtils.copyProperties(aggregate,guildDO);
        guildDO.setId(aggregate.getId().getValue());
        return guildDO;
    }

}
