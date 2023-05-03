package cn.catguild.auth.security;

import cn.catguild.auth.domain.CatAccount;
import cn.catguild.auth.domain.CatUser;
import cn.catguild.auth.domain.type.GuildId;
import cn.catguild.auth.service.CatUserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 认证用户信息查询服务
 *
 * @author xiyan
 * @date 2023/5/1 11:11
 */
@AllArgsConstructor
@Service
public class GuildUserDetailsService implements UserDetailsService {

    private CatUserService catUserService;

    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 能进行公会信息授权的仅有公会长可以
        CatUser catUser = catUserService.findGuildMasterByAccountName(new GuildId(0L),username);
        return toUserDetails(catUser);
    }

    private UserDetails toUserDetails(CatUser catUser) {
        CatAccount catAccount = catUser.getCatAccount();
        return User.withUsername(catAccount.getUsername())
                .password(catAccount.getPassword())
                .roles("Admin")
                .build();
    }


}
