package cn.catguild.user.controller;

import cn.catguild.common.api.ApiResponse;
import cn.catguild.user.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2022-08-04 17:45
 */
@RequestMapping("/users")
@RestController
@AllArgsConstructor
public class UserController {

	private final UserService userService;

	// 查询用户的分页列表
	public ApiResponse<?> page(){
		return ApiResponse.ok();
	}

	// 用户的详情
	@GetMapping("/{id}")
	public ApiResponse<?> detail(@PathVariable("id") Long id){
		return ApiResponse.ok(userService.get(id));
	}
	// 当前用户

}
