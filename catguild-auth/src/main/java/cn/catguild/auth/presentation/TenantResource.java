package cn.catguild.auth.presentation;

import cn.catguild.auth.application.TenantApplication;
import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.infrastructure.repository.domain.query.TenantQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/7/31 17:34
 */
@Slf4j
@RequestMapping("/tenants")
@RestController
public class TenantResource {

    @Resource
    private TenantApplication baseApplication;

    @GetMapping("")
    public ApiResponse<ApiPage<Tenant>> search(@ModelAttribute TenantQuery query) {
        ApiPage<Tenant> page = baseApplication.page(query);
        return ApiResponse.ok(page);
    }

    @GetMapping("/{id}")
    public ApiResponse<Tenant> getTenant(@PathVariable("id") Long id) {
        return ApiResponse.ok(baseApplication.findById(id));
    }

    @PostMapping
    public ApiResponse<Void> addTenant(@RequestBody Tenant tenant) {
        baseApplication.createTenant(tenant);
        return ApiResponse.ok();
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateTenant(@PathVariable("id") Long id, @RequestBody Tenant tenant) {
        tenant.setId(id);
        baseApplication.updateTenant(tenant);
        return ApiResponse.ok();
    }

    @GetMapping("/info")
    public ApiResponse<List<Tenant>> getTenantByDomainName(String domainName) {
        return ApiResponse.ok(baseApplication.getTenantByDomainName(domainName));
    }

    @PostMapping("/{id}/sync-app-resource")
    public ApiResponse<List<Tenant>> syncAppResource(@PathVariable("id") Long id, @RequestBody Tenant tenant) {
        baseApplication.syncAppResource(id, tenant);
        return ApiResponse.ok();
    }

    @PatchMapping("/{id}/switch-active-status")
    public ApiResponse<List<Tenant>> switchActiveStatus(@PathVariable("id") Long id) {
        baseApplication.switchActiveStatus(id);
        return ApiResponse.ok();
    }

}
