package cn.catguild.auth.domain.repository;

import cn.catguild.auth.domain.Resource;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/19 15:43
 */
public interface ResourceRepository {

    List<Resource> finByRoleIdsAndType(Long tenantId, Long appId, List<Long> roleIds, String resourceType);

}