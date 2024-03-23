package cn.catguild.auth.presentation;

import cn.catguild.auth.application.MenuApplication;
import cn.catguild.auth.domain.Menu;
import cn.catguild.common.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("")
    public ApiResponse<Void> addMenu(@RequestBody Menu menu) {
        return ApiResponse.ok();
    }

    @GetMapping("/info")
    public ApiResponse<List<Menu>> info() {
        List<Menu> tree = menuApplication.authTree();
        return ApiResponse.ok(tree);
    }

    @GetMapping("/tree")
    public ApiResponse<List<Menu>> menuTree() {
        // List<Menu> tree = menuApplication.tree();
        return ApiResponse.ok();
    }

}
