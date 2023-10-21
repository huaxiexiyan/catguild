package cn.catguild.auth.application;

import cn.catguild.auth.domain.CatRole;
import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.domain.repository.ResourceRepository;
import cn.catguild.auth.domain.repository.RoleRepository;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.common.entity.auth.TokenUser;
import cn.catguild.common.type.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        // 2、根据角色查询关联资源
        return resourceRepository.finByRoleIdsAndType(tokenUser.getTenantId(), appId, roleIds, resourceType);
    }

}
