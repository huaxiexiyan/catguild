package cn.catguild.auth.presentation;

import cn.catguild.auth.application.TenantApplicationService;
import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.presentation.model.TenantQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiyan
 * @date 2023/7/31 17:34
 */
@Slf4j
@RequestMapping("/tenants")
@RestController
public class TenantResource {

    @Resource
    private TenantApplicationService service;

    @GetMapping("")
    public ApiResponse<ApiPage<Tenant>> search(@ModelAttribute TenantQuery query) {
        ApiPage<Tenant> page = service.page(query);
        return ApiResponse.ok(page);
    }

    @GetMapping("/{id}")
    public ApiResponse<Tenant> getTenant(@PathVariable("id") Long id) {
        return ApiResponse.ok(service.findById(id));
    }

    @PostMapping
    public ApiResponse<Void> addTenant(@RequestBody Tenant tenant) {
        service.createTenant(tenant);
        return ApiResponse.ok();
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> updateTenant(@PathVariable("id") Long id,@RequestBody Tenant tenant) {
        tenant.setId(id);
        service.updateTenant(tenant);
        return ApiResponse.ok();
    }

    @GetMapping("/info")
    public ApiResponse<Tenant> getTenantByDomainName(String domainName) {
        return ApiResponse.ok(service.getTenantByDomainName(domainName));
    }

}
