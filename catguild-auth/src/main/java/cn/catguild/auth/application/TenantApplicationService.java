package cn.catguild.auth.application;

import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.infrastructure.TenantRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.auth.presentation.model.TenantQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/7/31 17:48
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class TenantApplicationService {

    private TenantRepository tenantRepository;

    private UserApplicationService userApplicationService;

    private IdGenerationClient idGenerationClient;

    /**
     * 新增租户
     * 1、先创建租户的基本信息
     * 2、建立默认的超管用户
     *
     * @param tenant
     */
    public void createTenant(Tenant tenant) {
        Integer uid = idGenerationClient.nextUid();
        Long id =  Long.valueOf(uid);
        tenant.setId(id);
        tenant.setCBy(AuthUtil.getLoginId());
        tenant.setCTime(LocalDateTime.now());
        // 生成全局唯一uid
        tenant.setUid(uid);
        tenant.setStatus(ActiveStatus.ACTIVE);
        tenantRepository.saveAndFlush(tenant);
        userApplicationService.registerTenantAdmin(tenant);
    }

    public Tenant findById(Long id) {
        return tenantRepository.findById(id).orElse(null);
    }

    public void updateTenant(Tenant account) {
        tenantRepository.saveAndFlush(account);
    }

    public ApiPage<Tenant> page(TenantQuery query) {
        Pageable pageable = PageRequest.of((int)query.getCurrent() - 1, (int)query.getSize());
        Page<Tenant> tenantPage = tenantRepository.findAll(pageable);
        ApiPage<Tenant> apiPage = new ApiPage<>();
        apiPage.setCurrent(tenantPage.getNumber());
        apiPage.setSize(tenantPage.getSize());
        apiPage.setTotal(tenantPage.getTotalElements());
        apiPage.setRecords(tenantPage.getContent());
        return apiPage;
    }

    public List<Tenant> getTenantByDomainName(String domainName) {
        return tenantRepository.findByDomainName(domainName);
    }

}
