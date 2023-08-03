package cn.catguild.auth.application;

import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.infrastructure.TenantRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.presentation.model.TenantQuery;
import cn.catguild.common.api.ApiPage;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author xiyan
 * @date 2023/7/31 17:48
 */
@Transactional(rollbackFor = Exception.class)
@Component
public class TenantApplicationService {

    @Resource
    private TenantRepository repository;

    @Resource
    private PasswordEncoder encoder;

    @Resource
    private IdGenerationClient idGenerationClient;

    public void createTenant(Tenant account) {
        Long id = idGenerationClient.nextId();
        account.setId(id);
        repository.saveAndFlush(account);
    }

    public Tenant findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void updateTenant(Tenant account) {
        repository.saveAndFlush(account);
    }

    public ApiPage<Tenant> page(TenantQuery query) {
        Pageable pageable = PageRequest.of((int)query.getCurrent() - 1, (int)query.getSize());
        Page<Tenant> tenantPage = repository.findAll(pageable);
        ApiPage<Tenant> apiPage = new ApiPage<>();
        apiPage.setCurrent(tenantPage.getNumber());
        apiPage.setSize(tenantPage.getSize());
        apiPage.setTotal(tenantPage.getTotalElements());
        apiPage.setRecords(tenantPage.getContent());
        return apiPage;
    }

}
