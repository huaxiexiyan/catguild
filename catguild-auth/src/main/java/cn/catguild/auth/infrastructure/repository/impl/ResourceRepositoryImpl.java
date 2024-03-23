package cn.catguild.auth.infrastructure.repository.impl;

import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.domain.repository.ResourceRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.infrastructure.repository.converter.ResourceDataConverter;
import cn.catguild.auth.infrastructure.repository.domain.entity.ResourceDO;
import cn.catguild.auth.infrastructure.repository.mapper.ResourceDOMapper;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.auth.presentation.model.ResourceQuery;
import cn.catguild.common.type.ActiveStatus;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/19 17:14
 */
@Slf4j
@AllArgsConstructor
@Repository
public class ResourceRepositoryImpl implements ResourceRepository {

    private final ResourceDOMapper baseMapper;

    private final ResourceDataConverter baseDataConverter;

    private final IdGenerationClient idClient;

    @Override
    public List<Resource> finByRoleIdsAndType(Long tenantId, Long appId, List<Long> roleIds, String resourceType) {
        List<ResourceDO> resources = baseMapper.selectByRoleIdsAndType(tenantId, appId, roleIds, resourceType, ActiveStatus.ACTIVE);
        return resources.stream().map(baseDataConverter::fromData).toList();
    }

    @Override
    public void save(Long tenantId, Resource resource) {
        Long loginId = AuthUtil.getLoginId();
        ResourceDO resourceDO = baseDataConverter.toData(resource);
        resourceDO.setTenantId(tenantId);
        if (resource.getId() != null && resource.getId() > 0) {
            // update
            resourceDO.setLastModifyBy(loginId);
            resourceDO.setLastModifyTime(LocalDateTime.now());
            baseMapper.updateById(resourceDO);
        } else {
            // insert
            resourceDO.setId(idClient.nextId());
            resourceDO.setCreateBy(loginId);
            resourceDO.setCreateTime(LocalDateTime.now());
            baseMapper.insert(resourceDO);
            resource.setId(resourceDO.getId());
        }
    }

    @Override
    public Resource findById(Long tenantId, Long id) {
        LambdaQueryWrapper<ResourceDO> queryWrapper = Wrappers.<ResourceDO>lambdaQuery()
                .eq(ResourceDO::getTenantId, tenantId)
                .eq(ResourceDO::getId, id);
        ResourceDO resourceDO = baseMapper.selectOne(queryWrapper);
        return baseDataConverter.fromData(resourceDO);
    }

    @Override
    public List<Resource> findByAppIdAndType(Long tenantId, Long appId, String refType) {
        LambdaQueryWrapper<ResourceDO> queryWrapper = Wrappers.<ResourceDO>lambdaQuery()
                .eq(ResourceDO::getTenantId, tenantId)
                .eq(ResourceDO::getAppId, appId)
                .eq(ResourceDO::getRefType, refType);
        List<ResourceDO> resourceDOS = baseMapper.selectList(queryWrapper);
        return baseDataConverter.fromData(resourceDOS);
    }

    @Override
    public void removeByIds(Long tenantId, Collection<Long> ids) {
        ids.forEach(id -> removeById(tenantId, id));
    }

    @Override
    public List<Resource> find(Long tenantId, Resource resource) {
        ResourceDO queryDO = baseDataConverter.toData(resource);
        return listResource(tenantId, queryDO);
    }

    @Override
    public List<Resource> listResource(Long tenantId, ResourceQuery resourceQuery) {
        ResourceDO queryDO = baseDataConverter.toData(resourceQuery);
        return listResource(tenantId, queryDO);
    }

    @Override
    public void removeById(Long tenantId, Long id) {
        baseMapper.deleteById(id);
    }

    private List<Resource> listResource(Long tenantId, ResourceDO queryDO) {
        queryDO.setTenantId(tenantId);
        List<ResourceDO> resourceDOS = baseMapper.selectList(Wrappers.<ResourceDO>lambdaQuery()
                .setEntity(queryDO));
        return baseDataConverter.fromData(resourceDOS);
    }

}
