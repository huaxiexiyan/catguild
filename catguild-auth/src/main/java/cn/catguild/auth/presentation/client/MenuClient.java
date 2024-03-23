package cn.catguild.auth.presentation.client;

import cn.catguild.auth.api.dto.request.MenuPageRequest;
import cn.catguild.auth.api.dto.request.MenuTreeRequest;
import cn.catguild.auth.api.dto.response.MenuResponse;
import cn.catguild.auth.api.dto.response.MenuTreeResponse;
import cn.catguild.auth.application.MenuApplication;
import cn.catguild.auth.application.command.MenuListCommand;
import cn.catguild.auth.application.command.MenuPageCommand;
import cn.catguild.auth.application.dto.MenuDTO;
import cn.catguild.auth.domain.type.MenuMeta;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import cn.catguild.common.type.CatTreeNode;
import cn.catguild.common.utility.ApiPageUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiyan
 * @date 2024/1/18 10:47
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/rpc/menus")
@RestController
public class MenuClient implements cn.catguild.auth.api.client.MenuClient {

	private final MenuApplication menuApplication;

	@PostMapping("/page")
	@Override
	public ApiResponse<ApiPage<MenuResponse>> page(@RequestBody MenuPageRequest pageRequest) {
		MenuPageCommand menuPageCommand = new MenuPageCommand();
		BeanUtils.copyProperties(pageRequest, pageRequest);
		ApiPage<MenuDTO> page = menuApplication.page(menuPageCommand);
		ApiPage<MenuResponse> menuDTOApiPage = ApiPageUtils.convertApiPage(page, (iPage) -> {
			List<MenuDTO> records = iPage.getRecords();
			return records.stream()
				.map(r -> {
					MenuResponse menu = new MenuResponse();
					BeanUtils.copyProperties(r, menu);
					return menu;
				}).toList();
		});
		return ApiResponse.ok(menuDTOApiPage);
	}

	@GetMapping("/list")
	@Override
	public ApiResponse<ApiPage<MenuResponse>> list() {
		return ApiResponse.ok();
	}

	@PostMapping("/tree")
	@Override
	public ApiResponse<List<MenuTreeResponse>> tree(@RequestParam String appCode,
													@RequestBody MenuTreeRequest menuTreeRequest) {
		MenuListCommand menuListCommand = new MenuListCommand();
		BeanUtils.copyProperties(menuTreeRequest, menuListCommand);

		List<MenuDTO> list = menuApplication.list(menuListCommand);
		List<MenuTreeResponse> responses = list.stream().map(r -> {
			MenuTreeResponse menuTreeResponse = new MenuTreeResponse();
			BeanUtils.copyProperties(r, menuTreeResponse);
			MenuMeta meta = r.getMeta();
			cn.catguild.auth.api.dto.type.MenuMeta menuMeta = new cn.catguild.auth.api.dto.type.MenuMeta();
			BeanUtils.copyProperties(meta, menuMeta);
			menuTreeResponse.setMeta(menuMeta);
			return menuTreeResponse;
		}).toList();
		List<MenuTreeResponse> menuTreeResponse = CatTreeNode.merge(responses);
		return ApiResponse.ok(menuTreeResponse);
	}

}
