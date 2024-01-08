package cn.catguild.auth.infrastructure.repository;

import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.domain.repository.ResourceRepository;
import cn.catguild.auth.domain.repository.TenantRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.infrastructure.repository.converter.TenantDataConverter;
import cn.catguild.auth.infrastructure.repository.domain.entity.TenantDO;
import cn.catguild.auth.infrastructure.repository.domain.query.TenantQuery;
import cn.catguild.auth.infrastructure.repository.mapper.TenantDOMapper;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.auth.presentation.model.ResourceQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.ActiveStatus;
import cn.catguild.common.utility.CollectionUtils;
import cn.catguild.common.utility.IPageUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/27 14:20
 */
@AllArgsConstructor
@Repository
public class TenantRepositoryImpl implements TenantRepository {

    private final TenantDOMapper baseMapper;

    private final TenantDataConverter baseDataConverter;

    private final IdGenerationClient idClient;

    private final ResourceRepository resourceRepository;

    @Override
    public void save(Tenant tenant) {
        Long loginId = AuthUtil.getLoginId();
        TenantDO tenantDO = baseDataConverter.toData(tenant);
        if (tenant.getId() != null && tenant.getId() > 0) {
            // update
            tenantDO.setLmBy(loginId);
            tenantDO.setLmTime(LocalDateTime.now());
            baseMapper.updateById(tenantDO);
        } else {
            // insert
            tenantDO.setId(idClient.nextId());
            tenantDO.setCBy(loginId);
            tenantDO.setCTime(LocalDateTime.now());
            tenantDO.setUid(idClient.nextUid());
            tenantDO.setActiveStatus(ActiveStatus.ACTIVE);
            baseMapper.insert(tenantDO);
            tenant.setId(tenantDO.getId());
        }
    }

    @Override
    public Tenant findById(Long id) {
        TenantDO tenantDO = baseMapper.selectById(id);
        Tenant tenant = baseDataConverter.fromData(tenantDO);
        compileApp(tenant);
        return tenant;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ApiPage<Tenant> page(TenantQuery query) {
        IPage<TenantDO> page = baseMapper.selectPage(query.getIpage(), Wrappers.emptyWrapper());
        return IPageUtils.toApiPage(page, (iPage) -> {
            List<TenantDO> records = (List<TenantDO>) iPage.getRecords();
            return records.stream()
                    .map(baseDataConverter::fromData)
                    .toList();
        });
    }

    @Override
    public Tenant findByDomainName(String domainName) {
        TenantDO tenantDOS = baseMapper.selectByDomainName(domainName);
        Tenant tenants = baseDataConverter.fromData(tenantDOS);
		compileApp(tenants);
        return tenants;
    }

    private void compileApp(Tenant tenant) {
		if (tenant == null){
			return;
		}
        ResourceQuery query = new ResourceQuery();
        query.setRefType("App");
        query.setActiveStatus(ActiveStatus.ACTIVE);
        List<Resource> resources = resourceRepository.listResource(tenant.getId(), query);
        if (CollectionUtils.isEmpty(resources)){
            return;
        }
        List<Long> appIds = resources.stream().map(Resource::getRefId).toList();
        tenant.setAppIds(appIds);
    }

}
