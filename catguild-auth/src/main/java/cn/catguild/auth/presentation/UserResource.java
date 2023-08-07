package cn.catguild.auth.presentation;

import cn.catguild.auth.application.UserApplicationService;
import cn.catguild.auth.domain.User;
import cn.catguild.auth.presentation.model.UserQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
	public ApiResponse<Void> addUser(@RequestBody User user) {
		service.addUser(user);
		return ApiResponse.ok();
	}

	@PutMapping("/{id}")
	public ApiResponse<Void> updateUser(@PathVariable("id") Long id,@RequestBody User user) {
		user.setId(id);
		service.updateUser(user);
		return ApiResponse.ok();
	}

	@GetMapping("/{id}")
	public ApiResponse<User> getUser(@PathVariable("id") Long id) {
		return ApiResponse.ok(service.findById(id));
	}

	@GetMapping("")
	public ApiResponse<ApiPage<User>> search(@ModelAttribute UserQuery query) {
		ApiPage<User> page = service.page(query);
		return ApiResponse.ok(page);
	}

}
