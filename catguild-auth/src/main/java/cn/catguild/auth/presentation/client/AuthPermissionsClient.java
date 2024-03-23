package cn.catguild.auth.presentation.client;

import cn.catguild.auth.api.dto.AuthPermissionDTO;
import cn.catguild.auth.application.PermissionsApplication;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.common.entity.auth.TokenUser;
import cn.catguild.common.utility.JSONUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiyan
 * @date 2024/1/8 16:30
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/rpc/permissions")
@RestController
public class AuthPermissionsClient {

	private final PermissionsApplication permissionsApplication;

	@GetMapping("/refType")
	public List<AuthPermissionDTO> getByRefType(@RequestParam String appCode,
												@RequestParam Integer refType){
		permissionsApplication.getByRefType(appCode,refType);
		TokenUser tokenUser = AuthUtil.getTokenUser();
		log.info("当前登录用户{}", JSONUtils.toJsonStr(tokenUser));
		return new ArrayList<>();
	}

}
