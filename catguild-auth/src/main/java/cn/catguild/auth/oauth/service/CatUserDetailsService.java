package cn.catguild.auth.oauth.service;

import cn.catguild.auth.domain.Account;
import cn.catguild.auth.domain.CatUser;
import cn.catguild.auth.infrastructure.AccountRepository;
import cn.catguild.auth.infrastructure.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author xiyan
 * @date 2023/6/6 17:48
 */
@AllArgsConstructor
@Service
public class CatUserDetailsService implements UserDetailsService {

    private AccountRepository accountRepository;

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        return User.withUsername(account.getUsername())
                        .password(account.getPassword())
						.disabled(account.getDisabled().isCode())
                        .build();
    }

    public CatUser getByUsername(String username){
        Account account = accountRepository.findByUsername(username);
        Optional<CatUser> catUser = userRepository.findById(account.getUserId());
        return catUser.orElse(null);
    }

}
