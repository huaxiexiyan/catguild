package cn.catguild.auth.presentation;

import cn.catguild.auth.application.AppApplicationService;
import cn.catguild.auth.domain.App;
import cn.catguild.auth.domain.AppVersion;
import cn.catguild.auth.presentation.model.AppQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/22 15:38
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/apps")
@RestController
public class AppResource {

    private AppApplicationService service;

    @GetMapping("")
    public ApiResponse<ApiPage<App>> pageApp(@ModelAttribute AppQuery query) {
        ApiPage<App> page = service.listApp(query);
        return ApiResponse.ok(page);
    }

    @GetMapping("/{id}")
    public ApiResponse<App> getApp(@PathVariable("id") Long id) {
        App app = service.getApp(id);
        return ApiResponse.ok(app);
    }

    @PostMapping
    public ApiResponse<Void> addApp(@RequestBody App app) {
        service.addApp(app);
        return ApiResponse.ok();
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateApp(@PathVariable("id") Long id, @RequestBody App app) {
        service.updateApp(id, app);
        return ApiResponse.ok();
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<Void> updateAppStatus(@PathVariable("id") Long id,
                                             @RequestBody App app) {
        service.updateAppStatus(id, app.getStatus());
        return ApiResponse.ok();
    }

    @GetMapping("/{id}/versions")
    public ApiResponse<List<AppVersion>> listAppVersion(@PathVariable("id") Long id) {
        List<AppVersion> appVersions = service.listAppVersionByAppId(id);
        return ApiResponse.ok(appVersions);
    }

    @PostMapping("/{id}/versions")
    public ApiResponse<Void> addAppVersion(@PathVariable("id") Long id,
                                           @RequestBody AppVersion appVersion) {
        service.addAppVersion(id, appVersion);
        return ApiResponse.ok();
    }

    @PutMapping("/{id}/versions/{versionId}")
    public ApiResponse<Void> updateAppVersion(@PathVariable("id") Long id,
                                              @PathVariable("versionId") Long versionId,
                                              @RequestBody AppVersion appVersion) {
        service.updateAppVersion(id, versionId, appVersion);
        return ApiResponse.ok();
    }

    @PatchMapping("/{id}/versions/{versionId}/status")
    public ApiResponse<Void> updateAppVersionStatus(@PathVariable("id") Long id,
                                                    @PathVariable("versionId") Long versionId,
                                                    @RequestBody AppVersion appVersion) {
        service.updateAppVersionStatus(id, versionId, appVersion.getStatus());
        return ApiResponse.ok();
    }

}
