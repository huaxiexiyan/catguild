package cn.catguild.auth.presentation;

import cn.catguild.auth.application.UserApplicationService;
import cn.catguild.auth.domain.CatUser;
import cn.catguild.auth.presentation.model.UserQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiyan
 * @date 2023/8/7 11:24
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/users")
@RestController
public class UserResource {

	@Resource
	private UserApplicationService service;

	@PostMapping
	public ApiResponse<Void> addUser(@RequestBody CatUser user) {
		service.addUser(user);
		return ApiResponse.ok();
	}

	@PutMapping("/{id}")
	public ApiResponse<Void> updateUser(@PathVariable("id") Long id,@RequestBody CatUser user) {
		user.setId(id);
		service.updateUser(user);
		return ApiResponse.ok();
	}

	@GetMapping("/{id}")
	public ApiResponse<CatUser> getUser(@PathVariable("id") Long id) {
		return ApiResponse.ok(service.findById(id));
	}

	@GetMapping("")
	public ApiResponse<ApiPage<CatUser>> search(@ModelAttribute UserQuery query) {
		ApiPage<CatUser> page = service.page(query);
		return ApiResponse.ok(page);
	}

	@GetMapping("/info")
	public ApiResponse<Map<String,Object>> info() {
		Map<String,Object> resultMap = new HashMap<>();
		resultMap.put("userId",1);
		resultMap.put("username","小趴菜");
		resultMap.put("realName", "小趴菜");
		resultMap.put("avatar", "");
		resultMap.put("desc", "小趴菜");

		List<Map<String,Object>> roles = new ArrayList<>();
		Map<String,Object> role = new HashMap<>();
		role.put("roleName","User");
		role.put("value","1");
		roles.add(role);
		resultMap.put("roles",roles);
		log.info("执行了");
		return ApiResponse.ok(resultMap);
	}

}
