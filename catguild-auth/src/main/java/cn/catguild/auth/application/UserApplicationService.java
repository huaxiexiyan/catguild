package cn.catguild.auth.application;

import cn.catguild.auth.domain.User;
import cn.catguild.auth.infrastructure.AccountRepository;
import cn.catguild.auth.infrastructure.UserRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.presentation.model.UserQuery;
import cn.catguild.common.api.ApiPage;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiyan
 * @date 2023/8/7 11:32
 */
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class UserApplicationService {

	private UserRepository userRepository;

	private AccountRepository accountRepository;

	private PasswordEncoder encoder;

	private IdGenerationClient idGenerationClient;

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

}
