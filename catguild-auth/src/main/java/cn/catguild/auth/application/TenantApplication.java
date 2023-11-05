package cn.catguild.auth.application;

import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.domain.Tenant;
import cn.catguild.auth.domain.repository.TenantRepository;
import cn.catguild.auth.infrastructure.adapter.external.client.AppClient;
import cn.catguild.auth.infrastructure.adapter.external.client.MenuClient;
import cn.catguild.auth.infrastructure.repository.domain.query.TenantQuery;
import cn.catguild.auth.presentation.model.ResourceQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.ActiveStatus;
import cn.catguild.common.utility.CollectionUtils;
import cn.catguild.system.api.dto.AppDTO;
import cn.catguild.system.api.dto.MenuDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2023/7/31 17:48
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class TenantApplication {

    private final TenantRepository tenantRepository;

    private final UserApplicationService userApplicationService;

    private final PermissionsApplication permissionsApplication;

    private final ResourceApplication resourceApplication;

    private final AppClient appClient;

    private final MenuClient menuClient;

    /**
     * 新增租户
     * 1、先创建租户的基本信息
     * 2、建立默认的超管用户
     *
     * @param tenant
     */
    public void createTenant(Tenant tenant) {
        tenantRepository.save(tenant);
        userApplicationService.registerTenantAdmin(tenant);
    }

    public Tenant findById(Long id) {
        return tenantRepository.findById(id);
    }

    public void updateTenant(Tenant account) {
        tenantRepository.save(account);
    }

    public ApiPage<Tenant> page(TenantQuery query) {
        return tenantRepository.page(query);
    }

    public List<Tenant> getTenantByDomainName(String domainName) {
        return tenantRepository.findByDomainName(domainName);
    }


    // todo 需要重构，魔法值
    public void syncAppResource(Long id, Tenant tenant) {
        // 目标结果
        List<Long> appIds = tenant.getAppIds();
        ResourceQuery query = new ResourceQuery();
        query.setRefType("App");
        List<Resource> resources = resourceApplication.listResource(id, query);
        if (CollectionUtils.isEmpty(appIds)){
            // 将app资源，置为非活跃状态（方便恢复）
            resources.forEach(r-> resourceApplication.switchActiveStatusInactive(id, r.getId()));
        }else {
            Map<Long, Resource> dbResourceMap = resources.stream().collect(Collectors.toMap(Resource::getAppId, Function.identity()));
            appIds.forEach(appId->{
                if (dbResourceMap.containsKey(appId)){
                    // 有，移除
                    Resource resource = dbResourceMap.get(appId);
                    if (resource.getActiveStatus() == ActiveStatus.INACTIVE){
                        resourceApplication.switchActiveStatusActive(id, resource.getId());
                    }
                    dbResourceMap.remove(appId);
                }else {
                    // 没有，新增
                    Resource resource = new Resource();
                    resource.setTenantId(id);
                    resource.setAppId(appId);
                    resource.setRefId(appId);
                    resource.setRefType("App");
                    resource.switchActiveStatusActive();
                    resourceApplication.addResource(id, resource);
                }
            });

            // 下线附属资源
            Set<Long> removedAppIds = dbResourceMap.keySet();
            if (CollectionUtils.isNotEmpty(removedAppIds)){
                removedAppIds.forEach(removedAppId->{
                    resourceApplication.inactiveResourceByAppIdAndType(id, removedAppId, "Menu");
                });
            }

            List<Long> resourceIds = dbResourceMap.values().stream().map(Resource::getId).toList();
            if (CollectionUtils.isNotEmpty(resourceIds)){
                resourceIds.forEach(resourceId-> resourceApplication.switchActiveStatusInactive(id, resourceId));
            }
        }

        syncAppMenuResource(id);
    }

    private void syncAppMenuResource(Long id) {
        //
        Tenant tenant = tenantRepository.findById(id);
        List<Long> appIds = tenant.getAppIds();
        // 查询出所有菜单
        List<AppDTO> apps = appClient.listApp(appIds);
        if (CollectionUtils.isEmpty(apps)){
            return;
        }
        List<MenuDTO> menus = apps.stream()
                .map(AppDTO::getMenus)
                .flatMap(Collection::stream)
                .toList();
        menus.forEach(m->{
            System.out.println(m.getMenuId());
        });
        // 同步资源
        Map<Long, List<MenuDTO>> menuMap = menus.stream()
                .collect(Collectors.groupingBy(MenuDTO::getAppId));
        menuMap.forEach((appId,menuList)->{
            List<Long> menuIds = menuList.stream().map(MenuDTO::getMenuId).toList();
            resourceApplication.syncResource(id, appId,"Menu", menuIds);
        });
    }

    public void switchActiveStatus(Long id) {
        Tenant tenant = tenantRepository.findById(id);
        tenant.switchActiveStatus();
        tenantRepository.save(tenant);
    }


}
