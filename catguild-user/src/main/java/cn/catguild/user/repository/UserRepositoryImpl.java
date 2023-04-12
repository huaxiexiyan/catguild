package cn.catguild.user.repository;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.domain.po.CatUserDO;
import cn.catguild.user.domain.query.CatUserQuery;
import cn.catguild.user.repository.mapper.CatUserMapper;
import cn.catguild.user.service.repository.UserRepository;
import cn.catguild.user.utility.IPageUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2022/9/30 15:56
 */
@Slf4j
@AllArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepository {

	private final CatUserMapper catUserMapper;

	@Override
	public CatUser save(Long guildId, CatUser catUser) {
		CatUserDO catUserDO = new CatUserDO();
		catUserDO.setGuildId(guildId);
		BeanUtils.copyProperties(catUser, catUserDO);
		catUserMapper.insert(catUserDO);
		return catUser;
	}

	@Override
	public void remove(Long guildId, Long id) {
		catUserMapper.delete(Wrappers.<CatUserDO>lambdaQuery()
			.eq(CatUserDO::getGuildId, guildId)
			.eq(CatUserDO::getId, id));
	}

	@Override
	public CatUser find(Long guildId, Long id) {
		CatUserDO catUserDO = catUserMapper.selectOne(Wrappers.<CatUserDO>lambdaQuery()
			.eq(CatUserDO::getGuildId, guildId)
			.eq(CatUserDO::getId, id));
		return new CatUser(catUserDO);
	}

	@Override
	public List<CatUser> list(Long guildId, CatUserQuery query) {
		return catUserMapper.selectList(Wrappers.<CatUserDO>lambdaQuery()
			.like(CatUserDO::getName, query.getNameLike())).stream()
			.map(CatUser::new)
			.collect(Collectors.toList());
	}

	@Override
	public ApiPage<CatUser> page(Long guildId, ApiPage<CatUser> apiPage, CatUserQuery query) {
		return IPageUtils.toApiPage(new ApiPage<>(),
			catUserMapper.selectPage(IPageUtils.toIPage(new Page<>(),apiPage),
			Wrappers.<CatUserDO>lambdaQuery()
				.like(CatUserDO::getName, query.getNameLike())));
	}

}
