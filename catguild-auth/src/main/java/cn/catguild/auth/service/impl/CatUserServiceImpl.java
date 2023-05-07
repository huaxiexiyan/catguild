package cn.catguild.auth.service.impl;

import cn.catguild.auth.domain.CatUser;
import cn.catguild.auth.domain.type.GuildId;
import cn.catguild.auth.service.CatUserService;
import cn.catguild.auth.service.repository.CatUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * @author xiyan
 * @date 2023/5/1 12:03
 */
@Slf4j
@AllArgsConstructor
@Service
public class CatUserServiceImpl implements CatUserService {

    private CatUserRepository catUserRepository;

    @Override
    public CatUser findGuildMaster(GuildId guild, String username) {
        return null;
    }

}
