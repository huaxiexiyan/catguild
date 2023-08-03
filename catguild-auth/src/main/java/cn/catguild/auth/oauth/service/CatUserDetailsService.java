package cn.catguild.auth.oauth.service;

import cn.catguild.auth.domain.Account;
import cn.catguild.auth.infrastructure.AccountRepository;
import cn.catguild.auth.infrastructure.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author xiyan
 * @date 2023/6/6 17:48
 */
@Service
public class CatUserDetailsService implements UserDetailsService {

    @Resource
    private UserRepository userRepository;
    @Resource
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        UserDetails userDetails =
                User.withUsername(account.getUsername())
                        .password(account.getPassword())
                        .roles("USER")
                        .build();
        return userDetails;
    }

}
