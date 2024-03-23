package cn.catguild.auth.application;

import cn.catguild.auth.domain.CatRole;
import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.domain.repository.ResourceRepository;
import cn.catguild.auth.domain.repository.RoleRepository;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.common.entity.auth.TokenUser;
import cn.catguild.common.type.ActiveStatus;
import cn.catguild.common.utility.CollectionUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2023/10/19 15:04
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class PermissionsApplication {

    private final RoleRepository roleRepository;

    private final ResourceRepository resourceRepository;

	private final RoleApplication roleApplication;

    /**
     * 获取授权资源列表
     *
     * @param appId
     * @param resourceType
     */
    public List<Resource> getAuthResourceByType(Long appId, String resourceType) {
        // 1、查询当前用户拥有的角色
        TokenUser tokenUser = AuthUtil.getTokenUser();
        List<CatRole> roles = roleRepository.findByAppIdAndUserId(tokenUser.getTenantId(), tokenUser.getUserId(), appId);
        List<Long> roleIds = roles.stream()
                .filter(r -> r.getActiveStatus() == ActiveStatus.ACTIVE)
                .map(CatRole::getId)
                .toList();
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        // 2、根据角色查询关联资源
        return resourceRepository.finByRoleIdsAndType(tokenUser.getTenantId(), appId, roleIds, resourceType);
    }

    /**
     * 同步资源
     *
     * @param appId
     * @param refType
     * @param resourceIds
     */
    public void syncResource(Long tenantId, Long appId, String refType, List<Long> resourceIds) {
        if (CollectionUtils.isEmpty(resourceIds)) {
            return;
        }
        // 保留添加，痕迹，所以逻辑删除的，不去恢复它
        List<Resource> resources = resourceRepository.findByAppIdAndType(tenantId, appId, refType);
        Set<Long> removeIds = new HashSet<>();
        Map<Long, Long> resourceMap = resources.stream()
                .collect(Collectors.toMap(Resource::getRefId, Resource::getId));
        if (CollectionUtils.isNotEmpty(resourceMap)) {
            removeIds.addAll(resourceMap.values());
        }
        // 新增注册
        resourceIds.forEach(resourceId -> {
            if (removeIds.contains(resourceId)) {
                // 已经存在这个关系了,保持原样
                removeIds.remove(resourceMap.get(resourceId));
            } else {
                // 没有，需要新增
                Resource resource = new Resource();
                resource.setAppId(appId);
                resource.setRefId(resourceId);
                resource.setRefType(refType);
                registerResource(tenantId, resource);
            }
        });
        // 剩下的 menuIdSet 的是需要删除的
        if (CollectionUtils.isNotEmpty(removeIds)) {
            removeResource(tenantId, removeIds);
        }
    }

    public void registerResource(Long tenantId, Resource resource) {
        resource.setTenantId(tenantId);
        resourceRepository.save(tenantId, resource);
    }

    public void removeResource(Long tenantId, Long id) {
        removeResource(tenantId, Collections.singleton(id));
    }

    public void removeResource(Long tenantId, Collection<Long> ids) {
        resourceRepository.removeByIds(tenantId, ids);
    }

    public void clearResource(Long tenantId, Long appId, String refType) {
        List<Resource> resources = resourceRepository.findByAppIdAndType(tenantId, appId, refType);
        if (CollectionUtils.isNotEmpty(resources)){
            List<Long> ids = resources.stream().map(Resource::getId)
                    .toList();
            removeResource(tenantId,ids);
        }
    }

    public void clearResource(Long tenantId, String refType) {
        Resource resource = new Resource();
        resource.setRefType(refType);
        List<Resource> resources = resourceRepository.find(tenantId, resource);
        if (CollectionUtils.isNotEmpty(resources)){
            List<Long> ids = resources.stream().map(Resource::getId)
                    .toList();
            removeResource(tenantId,ids);
        }
    }

	/**
	 * 1、获取到当前的用户在这个app下的所有角色
	 * 2、根据角色+类型，查询出所授权的资源
	 *
	 * @param appCode
	 * @param refType
	 */
	public void getByRefType(String appCode, Integer refType) {
		List<CatRole> userRoles = roleApplication.getByUserIdAndAppCode(AuthUtil.getLoginId(), appCode);
		if (CollectionUtils.isEmpty(userRoles)){
			return;
		}
		List<Long> roleIds = userRoles.stream().map(CatRole::getId).toList();

	}

}
