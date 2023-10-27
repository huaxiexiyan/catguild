package cn.catguild.auth.infrastructure.repository;

import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.domain.repository.TenantRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.infrastructure.repository.converter.TenantDataConverter;
import cn.catguild.auth.infrastructure.repository.domain.entity.TenantDO;
import cn.catguild.auth.infrastructure.repository.domain.query.TenantQuery;
import cn.catguild.auth.infrastructure.repository.domain.relation.TenantAppDO;
import cn.catguild.auth.infrastructure.repository.mapper.TenantAppDOMapper;
import cn.catguild.auth.infrastructure.repository.mapper.TenantDOMapper;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.ActiveStatus;
import cn.catguild.common.utility.CollectionUtils;
import cn.catguild.common.utility.IPageUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2023/10/27 14:20
 */
@AllArgsConstructor
@Repository
public class TenantRepositoryImpl implements TenantRepository {

    private final TenantDOMapper baseMapper;

    private final TenantAppDOMapper tenantAppMapper;

    private final TenantDataConverter baseDataConverter;

    private final IdGenerationClient idClient;

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
        return baseDataConverter.fromData(tenantDO);
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
    public void saveTenantApp(Tenant tenant) {
        List<Long> ids = tenant.getAppIds();
        if (CollectionUtils.isEmpty(ids)) {
            // 表示删掉掉所有的
            tenantAppMapper.delete(Wrappers.<TenantAppDO>lambdaQuery()
                    .eq(TenantAppDO::getTenantId, tenant.getId()));
            return;
        }

        // 保留添加，痕迹，所以逻辑删除的，不去恢复它
        List<TenantAppDO> appMenuDOS = tenantAppMapper.selectList(Wrappers.<TenantAppDO>lambdaQuery()
                .eq(TenantAppDO::getTenantId, tenant.getId()));
        Set<Long> removeIds = new HashSet<>();
        Set<Long> allSetTemp = appMenuDOS.stream()
                .map(TenantAppDO::getAppId)
                .collect(Collectors.toSet());
        if (CollectionUtils.isNotEmpty(allSetTemp)) {
            removeIds.addAll(allSetTemp);
        }

        ids.forEach(id -> {
            if (removeIds.contains(id)) {
                // 已经存在这个关系了,保持原样
                removeIds.remove(id);
            } else {
                TenantAppDO tenantAppDO = new TenantAppDO();
                // 没有，需要新增
                tenantAppDO.setId(idClient.nextId());
                tenantAppDO.setCBy(AuthUtil.getLoginId());
                tenantAppDO.setCTime(LocalDateTime.now());
                tenantAppDO.setActiveStatus(ActiveStatus.ACTIVE);
                tenantAppMapper.insert(tenantAppDO);
            }
        });
        // 剩下的 menuIdSet 的是需要删除的
        if (CollectionUtils.isNotEmpty(removeIds)) {
            tenantAppMapper.delete(Wrappers.<TenantAppDO>lambdaQuery()
                    .eq(TenantAppDO::getAppId, tenant.getId())
                    .in(TenantAppDO::getAppId, removeIds));
        }
    }

    @Override
    public List<Tenant> findByDomainName(String domainName) {
        List<TenantDO> tenants = baseMapper.selectListByLikeDomainName(domainName);
        return baseDataConverter.fromData(tenants);
    }

}
