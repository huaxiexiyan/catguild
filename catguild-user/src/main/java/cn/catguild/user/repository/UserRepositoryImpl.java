package cn.catguild.user.repository;

import cn.catguild.common.api.ApiPage;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.domain.vo.CatUserQuery;
import cn.catguild.user.repository.mapper.CatUserMapper;
import cn.catguild.user.service.repository.UserRepository;
import cn.catguild.user.utility.IPageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
	public void save(CatUser catUser) {
		catUserMapper.insert(catUser);
	}

	@Override
	public void remove(Long id) {
		catUserMapper.deleteById(id);
	}

	@Override
	public CatUser get(Long id) {
		return catUserMapper.selectById(id);
	}

	@Override
	public List<CatUser> list(CatUserQuery query) {
		LambdaQueryWrapper<CatUser> queryWrapper = Wrappers.lambdaQuery();
		return catUserMapper.selectList(queryWrapper);
	}

	@Override
	public ApiPage<CatUser> page(CatUserQuery query) {
		LambdaQueryWrapper<CatUser> queryWrapper = Wrappers.lambdaQuery();
		IPage<CatUser> page = catUserMapper.selectPage(IPageUtils.getIPage(query), queryWrapper);
		return IPageUtils.toApiPage(page);
	}

}
