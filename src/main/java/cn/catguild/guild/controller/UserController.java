package cn.catguild.guild.controller;

import cn.catguild.guild.common.ApiResponse;
import cn.catguild.guild.domain.entity.Account;
import cn.catguild.guild.domain.entity.CatUser;
import cn.catguild.guild.service.AccountService;
import cn.catguild.guild.service.CatUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2022-08-04 17:45
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

	private final CatUserService catUserService;
	private final AccountService accountService;

	@PostMapping("/register")
	public ApiResponse<Void> register(@RequestBody Account account){
		//创建账号
		accountService.save(account);
		//创建用户
		CatUser catUser = new CatUser();
		catUser.generateName();
		catUser.setAccountId(account.getId());
		catUserService.save(catUser);
		return ApiResponse.ok();
	}

}
