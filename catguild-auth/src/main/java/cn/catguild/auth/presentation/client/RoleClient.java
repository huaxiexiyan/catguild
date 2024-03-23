package cn.catguild.auth.presentation.client;

import cn.catguild.auth.application.RoleApplication;
import cn.catguild.auth.domain.CatRole;
import cn.catguild.auth.presentation.client.dto.RoleDTO;
import cn.catguild.auth.presentation.client.dto.RoleQueryDTO;
import cn.catguild.common.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2023/8/7 11:24
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/rpc/roles")
@RestController
public class RoleClient {

	private final RoleApplication roleApplication;

	@PostMapping("/add")
	public ApiResponse<Void> addRole(@RequestBody RoleDTO role) {
		CatRole catRole = new CatRole();
		BeanUtils.copyProperties(role, catRole);
		roleApplication.addRole(catRole);
		return ApiResponse.ok();
	}

	@PostMapping("/update")
	public ApiResponse<Void> updateRole(@RequestBody RoleDTO role) {
		CatRole catRole = new CatRole();
		BeanUtils.copyProperties(role, catRole);
		roleApplication.updateRole(role.getId(), catRole);
		return ApiResponse.ok();
	}

	@PostMapping("/remove")
	public ApiResponse<Void> removeRole(@RequestBody RoleQueryDTO roleQuery) {
		roleApplication.removeRole(roleQuery.getIds());
		return ApiResponse.ok();
	}


	// @GetMapping("/page")
	// public ApiResponse<ApiPage<RoleDTO>> page(@ModelAttribute RoleQueryDTO query) {
	// 	ApiPage<CatRole> page = roleApplication.page(query);
	// 	return ApiResponse.ok();
	// }
	//
	// @GetMapping("/list")
	// public ApiResponse<List<RoleDTO>> list(@ModelAttribute RoleQueryDTO query) {
	// 	List<CatRole> page = roleApplication.list(query);
	// 	return ApiResponse.ok();
	// }


}
