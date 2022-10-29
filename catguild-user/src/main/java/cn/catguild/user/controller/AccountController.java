package cn.catguild.user.controller;

import cn.catguild.common.api.ApiResponse;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2022-08-04 17:43
 */
@RequestMapping("/accounts")
@RestController
@AllArgsConstructor
public class AccountController {

	private final AccountService accountService;

	@PostMapping("/register")
	public ApiResponse<?> register(Account account){
		accountService.register(account);
		return ApiResponse.ok();
	}

}
