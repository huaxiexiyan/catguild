package cn.catguild.auth.infrastructure.repository.impl;

import cn.catguild.auth.domain.Menu;
import cn.catguild.auth.domain.repository.MenuRepository;
import cn.catguild.auth.domain.type.MenuMeta;
import cn.catguild.auth.infrastructure.repository.domain.entity.MenuDO;
import cn.catguild.auth.infrastructure.repository.domain.query.MenuQuery;
import cn.catguild.auth.infrastructure.repository.mapper.MenuMapper;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.utility.IPageUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xiyan
 * @date 2024/1/18 10:55
 */
@Slf4j
@AllArgsConstructor
@Repository
public class MenuRepositoryImpl implements MenuRepository {

	private final MenuMapper baseMapper;

	@Override
	public ApiPage<Menu> page(MenuQuery query) {
		LambdaQueryWrapper<MenuDO> queryWrapper = Wrappers.<MenuDO>lambdaQuery();
		IPage<MenuDO> page = baseMapper.selectPage(query.getIpage(), queryWrapper);
		return IPageUtils.toApiPage(page, (iPage) -> {
			List<MenuDO> records = iPage.getRecords();
			return records.stream()
				.map(r -> {
					Menu menu = new Menu();
					BeanUtils.copyProperties(r, menu);
					return menu;
				}).toList();
		});
	}

	@Override
	public List<Menu> list(MenuQuery menuQuery) {
		LambdaQueryWrapper<MenuDO> queryWrapper = Wrappers.<MenuDO>lambdaQuery();
		List<MenuDO> list = baseMapper.selectList(queryWrapper);
		return list.stream()
			.map(r -> {
				Menu menu = new Menu();
				BeanUtils.copyProperties(r, menu);
				MenuMeta meta = new MenuMeta();
				BeanUtils.copyProperties(r, meta);
				menu.setMeta(meta);
				return menu;
			}).toList();
	}

}
