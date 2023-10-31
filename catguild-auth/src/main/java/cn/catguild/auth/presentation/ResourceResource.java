package cn.catguild.auth.presentation;

import cn.catguild.auth.application.ResourceApplication;
import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.auth.presentation.model.ResourceQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiyan
 * @date 2023/8/22 15:38
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/resources")
@RestController
public class ResourceResource {

    private ResourceApplication service;

    @GetMapping("")
    public ApiResponse<ApiPage<Resource>> pageResource(@ModelAttribute ResourceQuery query) {
        ApiPage<Resource> page = service.pageResource(query);
        return ApiResponse.ok(page);
    }

    @GetMapping("/{id}")
    public ApiResponse<Resource> getResource(@PathVariable("id") Long id) {
        Resource Resource = service.getResource(id);
        return ApiResponse.ok(Resource);
    }

    @PostMapping
    public ApiResponse<Map<String, String>> addResource(@RequestBody Resource resource) {
        Long id = service.addResource(AuthUtil.getTenantId(),resource);
        Map<String, String> newData = new HashMap<>(1);
        newData.put("id", id.toString());
        return ApiResponse.ok(newData);
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateResource(@PathVariable("id") Long id, @RequestBody Resource resource) {
        service.updateResource(id, resource);
        return ApiResponse.ok();
    }

    @PatchMapping("/{id}/status")
    public ApiResponse<Void> updateResourceStatus(@PathVariable("id") Long id,
                                                  @RequestBody Resource resource) {
        //service.updateResourceStatus(id, resource.getStatus());
        return ApiResponse.ok();
    }

}
