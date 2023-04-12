package cn.catguild.user.repository;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.domain.query.CatUserQuery;
import cn.catguild.user.repository.mapper.CatUserMapper;
import cn.catguild.user.service.repository.UserRepository;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.List;

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
	public CatUser save(CatUser catUser) {
		catUserMapper.insert(catUser);
		return catUser;
	}

	@Override
	public void remove(Long id) {
		catUserMapper.deleteById(id);
	}

	@Override
	public CatUser find(Long id) {
		return catUserMapper.selectById(id);
	}

	@Override
	public List<CatUser> list(CatUserQuery query) {
		LambdaQueryWrapper<CatUser> queryWrapper = Wrappers.lambdaQuery();
		return catUserMapper.selectList(queryWrapper);
	}

	@Override
	public ApiPage<CatUser> page(ApiPage<CatUser> apiPage, CatUserQuery query) {
		//LambdaQueryWrapper<CatUser> queryWrapper = Wrappers.lambdaQuery();
		//IPage<CatUser> page = catUserMapper.selectPage(IPageUtils.getIPage(query), queryWrapper);
		//return IPageUtils.toApiPage(page);
		return null;
	}

}
