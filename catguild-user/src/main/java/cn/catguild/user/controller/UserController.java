package cn.catguild.user.controller;

import cn.catguild.common.api.ApiResponse;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.service.AccountService;
import cn.catguild.user.service.CatUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author xiyan
 * @date 2022-08-04 17:45
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user/users")
public class UserController {

	private final CatUserService catUserService;
	private final AccountService accountService;

	@GetMapping("/{id}")
	public ApiResponse<CatUser> get(@PathVariable("id") Long id){
		return ApiResponse.ok(catUserService.getById(id));
	}

	@GetMapping("")
	public ApiResponse<IPage<CatUser>> page(@RequestParam Map<String, Object> user, Page<CatUser> page){
		return ApiResponse.ok(catUserService.page(page));
	}

}
