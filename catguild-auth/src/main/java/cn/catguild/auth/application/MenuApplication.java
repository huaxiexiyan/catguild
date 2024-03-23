package cn.catguild.auth.application;

import cn.catguild.auth.application.command.MenuListCommand;
import cn.catguild.auth.application.command.MenuPageCommand;
import cn.catguild.auth.application.command.MenuTreeCommand;
import cn.catguild.auth.application.dto.MenuDTO;
import cn.catguild.auth.domain.Menu;
import cn.catguild.auth.domain.repository.MenuRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.infrastructure.repository.domain.query.MenuQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.CatTreeNode;
import cn.catguild.common.utility.ApiPageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/8 17:21
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class MenuApplication {

	private final IdGenerationClient idClient;

	private MenuRepository menuRepository;

	// private ResourceRepository resourceRepository;

	public ApiPage<MenuDTO> page(MenuPageCommand pageCommand) {
		MenuQuery menuQuery = new MenuQuery();
		BeanUtils.copyProperties(pageCommand, menuQuery);
		ApiPage<Menu> page = menuRepository.page(menuQuery);
		return ApiPageUtils.convertApiPage(page, (iPage) -> {
			List<Menu> records = iPage.getRecords();
			return records.stream()
				.map(r -> {
					MenuDTO menu = new MenuDTO();
					BeanUtils.copyProperties(r, menu);
					return menu;
				}).toList();
		});
	}


	public void addMenu(Menu menu) {
		// 注册资源
		// resourceRepository.findById(menu.getResourceId())
		//        .ifPresent(resource -> {
		//            // 添加菜单
		//            Long id = idClient.nextId();
		//            menu.setId(id);
		//            menu.setCreateTime(LocalDateTime.now());
		//            menuRepository.saveAndFlush(menu);
		//
		//            resource.setRefId(id);
		//            resourceRepository.saveAndFlush(resource);
		//        });
	}

	// /**
	//  * 获取菜单树
	//  * 1、获取当前登录用户信息
	//  * 2、获取租户的菜单
	//  * 3、获取租户下该用户的拥有权限的菜单
	//  * 4、组合菜单树返回
	//  *
	//  * @return
	//  */
	// public List<Menu> tree() {
	//     List<Menu> all = menuRepository.findAll();
	//     return CatTreeNode.merge(all);
	// }

	/**
	 * 获取当前app的，当前用户的菜单树
	 *
	 * @return
	 */
	public List<Menu> authTree() {
		// TokenUser tokenUser = AuthUtil.getTokenUser();
		// if (tokenUser.getAuthorityType() == UserAuthorityType.SUPER_ADMINISTRATOR) {
		// 	// 直接返回所有权限树
		// 	return tree();
		// }
		// return tree();
		return new ArrayList<>();
	}

	public List<MenuDTO> tree(MenuTreeCommand menuTreeCommand) {
		MenuQuery menuQuery = new MenuQuery();
		BeanUtils.copyProperties(menuTreeCommand, menuQuery);
		List<Menu> tree = menuRepository.list(menuQuery);
		tree = CatTreeNode.merge(tree);
		return CatTreeNode.copy(tree, MenuDTO.class);
	}

	public List<MenuDTO> list(MenuListCommand menuListCommand) {
		MenuQuery menuQuery = new MenuQuery();
		BeanUtils.copyProperties(menuListCommand, menuQuery);
		List<Menu> list = menuRepository.list(menuQuery);
		return list.stream().map(r -> {
			MenuDTO menuDTO = new MenuDTO();
			BeanUtils.copyProperties(r, menuDTO);
			return menuDTO;
		}).toList();
	}

}
