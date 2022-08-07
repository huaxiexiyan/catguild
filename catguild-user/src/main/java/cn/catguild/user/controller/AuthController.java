package cn.catguild.user.controller;

import cn.catguild.common.api.ApiResponse;
import cn.catguild.user.domain.auth.JwtCredentials;
import cn.catguild.user.domain.entity.Account;
import cn.catguild.user.domain.entity.CatUser;
import cn.catguild.user.service.AccountService;
import cn.catguild.user.service.CatUserService;
import cn.hutool.core.text.CharSequenceUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiyan
 * @date 2022-08-06 21:03
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class AuthController {

	private final CatUserService catUserService;
	private final AccountService accountService;
	private final PasswordEncoder passwordEncoder;

	@PostMapping("/register")
	public ApiResponse<Void> register(@RequestBody Account account){
		//创建账号
		account.setPassword(passwordEncoder.encode(account.getPassword()));
		accountService.save(account);
		//创建用户
		CatUser catUser = new CatUser();
		if (CharSequenceUtil.isBlank(catUser.getName())){
			catUser.generateName();
		}else {
			catUser.setName(account.getUsername());
		}
		catUser.setAccountId(account.getId());
		catUserService.save(catUser);
		return ApiResponse.ok();
	}

	@PostMapping("/login")
	public ApiResponse<JwtCredentials> login(@RequestBody Account account){
		JwtCredentials jwtCredentials = accountService.authorization(account);
		return ApiResponse.ok(jwtCredentials);
	}

	@GetMapping("/getUserInfo")
	public ApiResponse<?> getUserInfo(){
		Map<String,Object> map = new HashMap<>();
		map.put("username","123");
		return ApiResponse.ok(map);
	}

	@GetMapping("/logout")
	public ApiResponse<?> logout(){
		return ApiResponse.ok();
	}

}
