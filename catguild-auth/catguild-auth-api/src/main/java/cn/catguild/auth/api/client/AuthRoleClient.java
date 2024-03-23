package cn.catguild.auth.api.client;

import cn.catguild.auth.api.dto.AuthPermissionDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xiyan
 * @date 2024/1/8 16:21
 */
@FeignClient(
	value = "catguild-auth",
	contextId = "authRoleClient",
	path = "/rpc/roles"
)
public interface AuthRoleClient {

	@GetMapping("/page")
	List<AuthPermissionDTO> page(@RequestParam String appCode, @RequestParam String refType);

}
