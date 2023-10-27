package cn.catguild.auth.application;

import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.domain.repository.TenantRepository;
import cn.catguild.auth.infrastructure.repository.domain.query.TenantQuery;
import cn.catguild.common.api.ApiPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/7/31 17:48
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class TenantApplication {

    private TenantRepository tenantRepository;

    private UserApplicationService userApplicationService;

    /**
     * 新增租户
     * 1、先创建租户的基本信息
     * 2、建立默认的超管用户
     *
     * @param tenant
     */
    public void createTenant(Tenant tenant) {
        tenantRepository.save(tenant);
        userApplicationService.registerTenantAdmin(tenant);
    }

    public Tenant findById(Long id) {
        return tenantRepository.findById(id);
    }

    public void updateTenant(Tenant account) {
        tenantRepository.save(account);
    }

    public ApiPage<Tenant> page(TenantQuery query) {
        return tenantRepository.page(query);
    }

    public List<Tenant> getTenantByDomainName(String domainName) {
        return tenantRepository.findByDomainName(domainName);
    }

    public void onlyUpdateTenantApp(Long id, Tenant tenant) {
        tenant.setId(id);
        tenantRepository.saveTenantApp(tenant);
    }

    public void switchActiveStatus(Long id) {
        Tenant tenant = tenantRepository.findById(id);
        tenant.switchActiveStatus();
        tenantRepository.save(tenant);
    }

}
