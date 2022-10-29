package cn.catguild.user.service.impl;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.domain.vo.CatUserQuery;
import cn.catguild.user.service.UserService;
import cn.catguild.user.service.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 *
 * @author xiyan
 * @date 2022/9/30 15:54
 */
@AllArgsConstructor
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	private final UserRepository catUserRepository;

	@Override
	public void add(CatUser catUser) {
		catUserRepository.save(catUser);
	}

	@Override
	public void remove(Long id) {
		catUserRepository.remove(id);
	}

	@Override
	public CatUser get(Long id) {
		return catUserRepository.get(id);
	}

	@Override
	public List<CatUser> list(CatUserQuery query) {
		return catUserRepository.list(query);
	}

	@Override
	public ApiPage<CatUser> page(CatUserQuery query) {
		return catUserRepository.page(query);
	}

}
