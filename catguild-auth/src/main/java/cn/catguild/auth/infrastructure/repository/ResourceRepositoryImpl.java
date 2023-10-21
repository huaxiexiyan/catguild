package cn.catguild.auth.infrastructure.repository;

import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.domain.repository.ResourceRepository;
import cn.catguild.auth.infrastructure.repository.converter.ResourceDataConverter;
import cn.catguild.auth.infrastructure.repository.entity.ResourceDO;
import cn.catguild.auth.infrastructure.repository.mapper.ResourceDOMapper;
import cn.catguild.common.type.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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

    private final ResourceDataConverter resourceDataConverter;

    @Override
    public List<Resource> finByRoleIdsAndType(Long tenantId, Long appId, List<Long> roleIds, String resourceType) {
        List<ResourceDO> resources = baseMapper.selectByRoleIdsAndType(tenantId, appId, roleIds, resourceType, ActiveStatus.ACTIVE);
        return resources.stream().map(resourceDataConverter::fromData).toList();
    }

}
