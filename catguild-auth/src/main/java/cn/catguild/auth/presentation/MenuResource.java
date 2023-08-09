package cn.catguild.auth.presentation;

import cn.catguild.auth.application.MenuApplicationService;
import cn.catguild.auth.domain.Menu;
import cn.catguild.common.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
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

    private MenuApplicationService menuApplicationService;

    @GetMapping("/info")
    public ApiResponse<Object> info() {
        List<Menu> tree = menuApplicationService.tree();
        return ApiResponse.ok(tree);
    }

}
