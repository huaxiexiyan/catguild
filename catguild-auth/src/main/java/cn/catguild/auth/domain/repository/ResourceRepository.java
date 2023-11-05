package cn.catguild.auth.domain.repository;

import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.presentation.model.ResourceQuery;

import java.util.Collection;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/19 15:43
 */
public interface ResourceRepository {

    List<Resource> finByRoleIdsAndType(Long tenantId, Long appId, List<Long> roleIds, String resourceType);

    void save(Long tenantId, Resource resource);

    Resource findById(Long tenantId, Long id);

    List<Resource> findByAppIdAndType(Long tenantId, Long appId, String refType);

    void removeByIds(Long tenantId, Collection<Long> removeIds);

    List<Resource> find(Long tenantId, Resource resource);

    List<Resource> listResource(Long tenantId, ResourceQuery resourceQuery);

    void removeById(Long tenantId, Long id);

}
