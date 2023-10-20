package cn.catguild.business.automatic.presentation;

import cn.catguild.business.automatic.application.AppAuthConfigApplication;
import cn.catguild.business.automatic.domain.AppAuthConfig;
import cn.catguild.business.automatic.infrastructure.domain.query.AppAuthConfigQuery;
import cn.catguild.business.util.AuthUtil;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiyan
 * @date 2023/8/8 16:25
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/automatic/app-auth-config")
@RestController
public class AppAuthConfigResource {

    private final AppAuthConfigApplication baseApplication;

    @GetMapping("")
    public ApiResponse<ApiPage<AppAuthConfig>> page(@ModelAttribute AppAuthConfigQuery query) {
        return ApiResponse.ok(baseApplication.page(AuthUtil.getTenantId(), query));
    }

    @PostMapping("")
    public ApiResponse<Void> add(@RequestBody AppAuthConfig appAuthConfig) {
        baseApplication.add(AuthUtil.getTenantId(), appAuthConfig);
        return ApiResponse.ok();
    }

}
