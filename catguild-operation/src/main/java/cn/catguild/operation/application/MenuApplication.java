package cn.catguild.operation.application;

import cn.catguild.auth.api.client.MenuClient;
import cn.catguild.auth.api.dto.request.MenuPageRequest;
import cn.catguild.auth.api.dto.request.MenuTreeRequest;
import cn.catguild.auth.api.dto.response.MenuResponse;
import cn.catguild.auth.api.dto.response.MenuTreeResponse;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import cn.catguild.common.type.CatTreeNode;
import cn.catguild.common.utility.ApiPageUtils;
import cn.catguild.operation.application.command.MenuPageCommand;
import cn.catguild.operation.application.command.MenuTreeCommand;
import cn.catguild.operation.application.dto.MenuDTO;
import cn.catguild.operation.domain.Menu;
import cn.catguild.operation.infrastructure.adapter.client.PermissionsClient;
import cn.catguild.operation.infrastructure.adapter.client.dto.PermissionDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiyan
 * @date 2024/1/8 15:37
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class MenuApplication {

	private final MenuClient menuClient;

	private final PermissionsClient permissionsClient;

	/**
	 * 获取登录的权限菜单
	 * 1、获取到当前app的所有菜单
	 * 2、获取当前用户的菜单权限
	 *
	 * @return
	 */
	public List<Menu> authTree() {
		List<PermissionDTO> permissions = permissionsClient.getByRefType(Menu.class.getSimpleName());
		List<Long> permissionIds = permissions.stream().map(PermissionDTO::getRefId).toList();
		// List<Menu> authMenus = menuClient.getByIds(permissionIds);
		// CatTreeNode.merge(authMenus);
		// return authMenus;
		return null;
	}

	public ApiPage<MenuDTO> page(MenuPageCommand pageCommand) {
		MenuPageRequest menuPageRequest = new MenuPageRequest();
		BeanUtils.copyProperties(pageCommand, menuPageRequest);
		ApiResponse<ApiPage<MenuResponse>> pageResponse = menuClient.page(menuPageRequest);
		return ApiPageUtils.convertApiPage(pageResponse.getData(), (iPage) -> {
			List<MenuResponse> records = iPage.getRecords();
			return records.stream()
				.map(r -> {
					MenuDTO menu = new MenuDTO();
					BeanUtils.copyProperties(r, menu);
					return menu;
				}).toList();
		});
	}

	public List<MenuDTO> tree(MenuTreeCommand menuTreeCommand) {
		MenuTreeRequest menuTreeRequest = new MenuTreeRequest();
		BeanUtils.copyProperties(menuTreeCommand, menuTreeRequest);
		ApiResponse<List<MenuTreeResponse>> treeResponse = menuClient.tree("s", menuTreeRequest);
		List<MenuTreeResponse> data = treeResponse.getData();
		return CatTreeNode.copy(data, MenuDTO.class);
	}

}
