package cn.catguild.auth.presentation;

import cn.catguild.auth.application.RoleApplication;
import cn.catguild.auth.domain.CatRole;
import cn.catguild.auth.presentation.client.dto.RoleDTO;
import cn.catguild.auth.presentation.client.dto.RoleQueryDTO;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

	@PostMapping("/add")
	public ApiResponse<Void> addRole(@RequestBody CatRole catRole) {
		roleApplication.addRole(catRole);
		return ApiResponse.ok();
	}

	@PostMapping("/update")
	public ApiResponse<Void> updateRole(Long id, @RequestBody CatRole catRole) {
		roleApplication.updateRole(id, catRole);
		return ApiResponse.ok();
	}

	@PostMapping("/remove")
	public ApiResponse<Void> removeRole(@RequestBody RoleQueryDTO roleQuery) {
		roleApplication.removeRole(roleQuery.getIds());
		return ApiResponse.ok();
	}


	@GetMapping("/page")
	public ApiResponse<ApiPage<RoleDTO>> page(@ModelAttribute RoleQueryDTO query) {
//		ApiPage<CatRole> page = roleApplication.page(query);
		return ApiResponse.ok();
	}

	@GetMapping("/list")
	public ApiResponse<List<RoleDTO>> list(@ModelAttribute RoleQueryDTO query) {
//		List<CatRole> page = roleApplication.list(query);
		return ApiResponse.ok();
	}


}
