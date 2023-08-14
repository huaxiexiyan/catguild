package cn.catguild.auth.application;

import cn.catguild.auth.domain.Account;
import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.domain.User;
import cn.catguild.auth.domain.type.UserAuthorityType;
import cn.catguild.auth.infrastructure.AccountRepository;
import cn.catguild.auth.infrastructure.UserRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.EmailClient;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.presentation.model.UserQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.ActiveStatus;
import cn.catguild.common.type.YesNoStatus;
import cn.catguild.common.utility.RandomPasswordGenerator;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

/**
 * @author xiyan
 * @date 2023/8/7 11:32
 */
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class UserApplicationService {

    private final UserRepository userRepository;

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final IdGenerationClient idGenerationClient;

    private final EmailClient emailClient;

    /**
     * 新增用户
     *
     * @param user
     */
    public void addUser(User user) {

    }

    public void updateUser(User user) {
    }

    public User findById(Long id) {
        return null;
    }

    public ApiPage<User> page(UserQuery query) {
        return null;
    }

    /**
     * 注册租户的超级管理员
     */
    public void registerTenantAdmin(Tenant tenant) {
        // 先创建一个超级管理用户
        User user = new User();
        user.setId(idGenerationClient.nextId());
        user.setTenantId(tenant.getId());
        user.setCBy(tenant.getCBy());
        user.setCTime(tenant.getCTime());

        user.setName(tenant.getName() + "@超级管理员");
        user.setAuthorityType(UserAuthorityType.SUPER_ADMINISTRATOR);
        user.setStatus(ActiveStatus.ACTIVE);
        userRepository.saveAndFlush(user);

        // 然后再为其创建一个登录账号
        Account account = new Account();
        account.setId(idGenerationClient.nextId());
        account.setTenantId(user.getTenantId());
        account.setCBy(user.getCBy());
        account.setCTime(user.getCTime());
        // 随机生成密码-并由邮件发送出去
        account.setUserId(user.getId());
        account.setUsername(tenant.getEmail());
        String password = RandomPasswordGenerator.generateRandomPassword();
        HashMap<String, String> tpParam = new HashMap<>() {{
            put("domain", "http://localhost:3002");
            put("password", password);
        }};
        emailClient.sendHtmlTp(tenant.getEmail(), "猫公会租户注册", 1L, tpParam);
        account.setPassword(passwordEncoder.encode(password));
        account.setDisabled(YesNoStatus.NO);
        accountRepository.saveAndFlush(account);
    }

}
