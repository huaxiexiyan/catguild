package cn.catguild.auth.service.impl;

import cn.catguild.auth.service.GuildService;
import cn.catguild.auth.service.repository.GuildRepository;
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

}
