package cn.catguild.auth.service.repository;

import cn.catguild.auth.domain.Guild;
import cn.catguild.auth.domain.type.GuildId;

/**
 * 1、接口名称不应该使用底层实现的语法
 * 2、出参入参不应该使用底层数据格式
 *
 *
 * @author xiyan
 * @date 2023/5/7 20:36
 */
public interface GuildRepository{

    Guild find(GuildId id) ;

    void remove(Guild aggregate);

    void save(Guild aggregate);

}
