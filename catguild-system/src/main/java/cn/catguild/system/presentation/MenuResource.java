package cn.catguild.system.presentation;

import cn.catguild.common.api.ApiResponse;
import cn.catguild.system.application.MenuApplication;
import cn.catguild.system.domain.Menu;
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
@RequestMapping("/menus")
@RestController
public class MenuResource {

    private MenuApplication menuApplication;

    @GetMapping("/tree")
    public ApiResponse<List<Menu>> menuTree() {
        return ApiResponse.ok(menuApplication.tree());
    }

    @PostMapping("")
    public ApiResponse<Void> addMenu(@RequestBody Menu menu) {
        menuApplication.addMenu(menu);
        return ApiResponse.ok();
    }

    @GetMapping("/{id}")
    public ApiResponse<Menu> menuDetail(@PathVariable("id") Long id) {
        return ApiResponse.ok(menuApplication.getById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateMenu(@PathVariable("id") Long id, @RequestBody Menu menu) {
        menuApplication.updateMenu(id, menu);
        return ApiResponse.ok();
    }

    @PatchMapping("/active-status/{id}")
    public ApiResponse<Void> patchStatus(@PathVariable("id") Long id, @RequestBody Menu menu) {
        menuApplication.patchStatus(id, menu);
        return ApiResponse.ok();
    }

    @PostMapping("/refresh-resource-point")
    public ApiResponse<Void> generateResourcePoint() {
        return ApiResponse.ok();
    }

    @PostMapping("/refresh-resource-point/{id}")
    public ApiResponse<Void> generateResourcePoint(@PathVariable("id") Long id) {
        return ApiResponse.ok();
    }

    @GetMapping("/auth-tree/{appId}")
    public ApiResponse<Void> authMenuTree(@PathVariable("appId") Long appId) {
        List<Menu> menus = menuApplication.getAuthMenuTree(appId);
        return ApiResponse.ok();
    }

}
