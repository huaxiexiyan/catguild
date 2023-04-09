package cn.catguild.user.controller;

import lombok.AllArgsConstructor;
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

	//private final UserService userService;
	//
	//// 查询用户的分页列表
	//public ApiResponse<?> page(){
	//	return ApiResponse.ok();
	//}
	//
	//// 用户的详情
	//@GetMapping("/{id}")
	//public ApiResponse<?> detail(@PathVariable("id") Long id){
	//	return ApiResponse.ok(userService.get(id));
	//}
	// 当前用户

	/**
	 * 3、创建用户
	 */
	/**
	 * 2、修改用户信息
	 */
	/**
	 * 4、删除用户
	 */
	/**
	 * 5、获取用户信息
	 */
	/**
	 * 1、分页获取用户的列表（租户id必填）
	 */

}
