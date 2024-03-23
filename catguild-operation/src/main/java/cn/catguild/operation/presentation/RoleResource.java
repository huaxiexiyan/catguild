package cn.catguild.operation.presentation;

import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import cn.catguild.operation.application.RoleApplication;
import cn.catguild.operation.application.command.RolePageCommand;
import cn.catguild.operation.application.dto.RoleDTO;
import cn.catguild.operation.presentation.request.RoleQueryRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2023/8/7 11:24
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/roles")
@RestController
public class RoleResource {

	private final RoleApplication roleApplication;

	// @PostMapping("/add")
	// public ApiResponse<Void> addRole(@RequestBody CatRole catRole) {
	// 	roleApplication.addRole(catRole);
	// 	return ApiResponse.ok();
	// }
	//
	// @PostMapping("/update")
	// public ApiResponse<Void> updateRole(Long id, @RequestBody CatRole catRole) {
	// 	roleApplication.updateRole(id, catRole);
	// 	return ApiResponse.ok();
	// }
	//
	// @PostMapping("/remove")
	// public ApiResponse<Void> removeRole(@RequestBody RoleQueryDTO roleQuery) {
	// 	roleApplication.removeRole(roleQuery.getIds());
	// 	return ApiResponse.ok();
	// }


	@GetMapping("/page")
	public ApiResponse<ApiPage<RoleDTO>> page(@ModelAttribute RoleQueryRequest query) {
		RolePageCommand rolePageCommand = new RolePageCommand();
		BeanUtils.copyProperties(query, rolePageCommand);
		ApiPage<RoleDTO> page = roleApplication.page(rolePageCommand);
		return ApiResponse.ok(page);
	}


}
