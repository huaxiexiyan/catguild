package cn.catguild.auth.api.client;

import cn.catguild.auth.api.client.fallback.MenuClientFallback;
import cn.catguild.auth.api.dto.request.MenuPageRequest;
import cn.catguild.auth.api.dto.request.MenuTreeRequest;
import cn.catguild.auth.api.dto.response.MenuResponse;
import cn.catguild.auth.api.dto.response.MenuTreeResponse;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author xiyan
 * @date 2024/1/18 10:27
 */
@FeignClient(
	value = "catguild-auth",
	contextId = "menuClient",
	path = "/rpc/menus",
	fallback = MenuClientFallback.class
)
public interface MenuClient {

	@PostMapping("/page")
	ApiResponse<ApiPage<MenuResponse>> page(@RequestBody MenuPageRequest pageRequest);

	@GetMapping("/list")
	ApiResponse<ApiPage<MenuResponse>> list();

	@PostMapping("/tree")
	ApiResponse<List<MenuTreeResponse>> tree(@RequestParam String appCode, @RequestBody MenuTreeRequest menuTreeRequest);

}
