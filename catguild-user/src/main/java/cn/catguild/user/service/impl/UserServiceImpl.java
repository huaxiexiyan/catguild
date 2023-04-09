package cn.catguild.user.service.impl;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.domain.query.CatUserQuery;
import cn.catguild.user.service.UserService;
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


	@Override
	public CatUser add(Long guildId, CatUser catuser) {
		return null;
	}

	@Override
	public CatUser get(Long guildId, Long catUserId) {
		return null;
	}

	@Override
	public CatUser get(Long guildId, CatUserQuery query) {
		return null;
	}

	@Override
	public List<CatUser> list(Long guildId, CatUserQuery query) {
		return null;
	}

	@Override
	public ApiPage<CatUser> page(Long guildId, ApiPage<CatUser> apiPage, CatUserQuery query) {
		return null;
	}

}
