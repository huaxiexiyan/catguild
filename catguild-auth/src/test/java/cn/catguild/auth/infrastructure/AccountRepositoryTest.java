package cn.catguild.auth.infrastructure;

import cn.catguild.auth.domain.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    public void add(){
        Account account = new Account();
        account.setId(1L);
        account.setUsername("user");
        account.setPassword(passwordEncoder.encode("password"));
        accountRepository.saveAndFlush(account);
    }

}