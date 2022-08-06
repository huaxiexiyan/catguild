package cn.catguild.user.controller;

import cn.catguild.user.service.AccountService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2022-08-04 17:43
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/account")
public class AccountController {

	private final AccountService accountService;

}
