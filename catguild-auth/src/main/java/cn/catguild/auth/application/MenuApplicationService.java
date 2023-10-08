package cn.catguild.auth.application;

import cn.catguild.auth.domain.CatRole;
import cn.catguild.auth.domain.Menu;
import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.domain.type.ResourceType;
import cn.catguild.auth.infrastructure.AppRepository;
import cn.catguild.auth.infrastructure.MenuRepository;
import cn.catguild.auth.infrastructure.ResourceRepository;
import cn.catguild.auth.infrastructure.RoleRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.auth.oauth.constant.TokenConstant;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.common.type.CatTreeNode;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiyan
 * @date 2023/8/8 17:21
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class MenuApplicationService {

    private final IdGenerationClient idClient;

    private MenuRepository menuRepository;

    private ResourceRepository resourceRepository;

    private RoleRepository roleRepository;

    private AppRepository appRepository;

    public void addMenu(Menu menu) {
        // 注册资源
        resourceRepository.findById(menu.getResourceId())
                .ifPresent(resource -> {
                    // 添加菜单
                    Long id = idClient.nextId();
                    menu.setId(id);
                    menu.setCTime(LocalDateTime.now());
                    menuRepository.saveAndFlush(menu);

                    resource.setRefId(id);
                    resourceRepository.saveAndFlush(resource);
                });
    }

    /**
     * 获取菜单树
     * 1、获取当前登录用户信息
     * 2、获取租户的菜单
     * 3、获取租户下该用户的拥有权限的菜单
     * 4、组合菜单树返回
     *
     * @return
     */
    public List<Menu> tree() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        Jwt token = ((JwtAuthenticationToken) authentication).getToken();
        Map<String, Object> claims = token.getClaims();
        Object o = claims.get(TokenConstant.TENANT_ID);
        List<Menu> all = menuRepository.findAll();
        return CatTreeNode.merge(all);
    }

    public List<Menu> authTree() {
        Long loginId = AuthUtil.getLoginId();
        Long tenantId = AuthUtil.getTenantId();
        // 根据用户id，查询出菜单
        List<CatRole> roles = roleRepository.findByUserId(tenantId, loginId);
        List<Long> roleIds = roles.stream().map(CatRole::getId).toList();
        if (CollectionUtils.isEmpty(roleIds)) {
            return new ArrayList<>();
        }
        List<Resource> resources = resourceRepository.findByTypeAndRoleIdsInAppVersionId(tenantId, ResourceType.MENU,
                roleIds, 1L);
        if (CollectionUtils.isEmpty(resources)) {
            return new ArrayList<>();
        }
        List<Long> resourceIds = resources.stream().map(Resource::getId).toList();
        List<Menu> authMenus = menuRepository.findByResourceIds(resourceIds);
        if (CollectionUtils.isEmpty(authMenus)) {
            return new ArrayList<>();
        }
        return CatTreeNode.merge(authMenus);
    }

}
