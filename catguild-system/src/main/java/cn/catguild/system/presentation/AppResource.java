package cn.catguild.system.presentation;

import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import cn.catguild.system.application.AppApplication;
import cn.catguild.system.domain.App;
import cn.catguild.system.infrastructure.domain.query.AppQuery;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/8 16:25
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/apps")
@RestController
public class AppResource {

    private AppApplication baseApplication;

    @GetMapping("")
    public ApiResponse<ApiPage<App>> page(@ModelAttribute AppQuery query) {
        return ApiResponse.ok(baseApplication.page(query));
    }

    @GetMapping("/tree")
    public ApiResponse<List<App>> tree() {
        return ApiResponse.ok(baseApplication.tree());
    }

    @GetMapping("/main-app")
    public ApiResponse<List<App>> getMainApp() {
        return ApiResponse.ok(baseApplication.getMainApp());
    }

    @PostMapping("")
    public ApiResponse<Void> addApp(@RequestBody App app) {
        baseApplication.addApp(app);
        return ApiResponse.ok();
    }

    @PatchMapping("/{id}")
    public ApiResponse<Void> updateApp(@PathVariable("id") Long id, @RequestBody App app) {
        baseApplication.updateApp(app);
        return ApiResponse.ok();
    }

    @PatchMapping("/{id}/active-status")
    public ApiResponse<Void> updateAppActiveStatus(@PathVariable("id") Long id) {
        baseApplication.updateAppActiveStatus(id);
        return ApiResponse.ok();
    }


}
