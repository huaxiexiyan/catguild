package cn.catguild.system.infrastructure.repository;

import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.CatTreeNode;
import cn.catguild.common.utility.CollectionUtils;
import cn.catguild.common.utility.IPageUtils;
import cn.catguild.common.utility.StringUtils;
import cn.catguild.system.domain.App;
import cn.catguild.system.domain.Menu;
import cn.catguild.system.domain.repositroy.AppRepository;
import cn.catguild.system.domain.repositroy.MenuRepository;
import cn.catguild.system.infrastructure.converter.AppDataConverter;
import cn.catguild.system.infrastructure.domain.AppDO;
import cn.catguild.system.infrastructure.domain.query.AppQuery;
import cn.catguild.system.infrastructure.domain.relation.AppMenuDO;
import cn.catguild.system.infrastructure.idgeneration.IdGenerationService;
import cn.catguild.system.infrastructure.repository.mapper.AppDOMapper;
import cn.catguild.system.infrastructure.repository.mapper.AppMenuDOMapper;
import cn.catguild.system.util.AuthUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Splitter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2023/10/13 14:05
 */
@Slf4j
@AllArgsConstructor
@Component
public class AppRepositoryImpl implements AppRepository {

    private final AppDOMapper baseMapper;
    private final AppMenuDOMapper appMenuDOMapper;

    private final MenuRepository menuRepository;

    private final AppDataConverter baseDataConverter;

    private final IdGenerationService idService;

    @Override
    public void save(App app) {
        Long loginId = AuthUtil.getLoginId();
        if (app.getId() != null && app.getId() > 0) {
            // update
            AppDO appDO = baseDataConverter.toData(app);
            appDO.setLastModifyBy(loginId);
            appDO.setLastModifyTime(LocalDateTime.now());
            baseMapper.updateById(appDO);
        } else {
            // insert
            AppDO appDO = baseDataConverter.toData(app);
            appDO.setId(Long.valueOf(idService.nextUID()));
            appDO.setCreateBy(loginId);
            appDO.setCreateTime(LocalDateTime.now());
            // 构建路径
            if (appDO.getParentId() == null) {
                appDO.setParentId(0L);
            }
            if (appDO.getParentId() != 0L) {
                AppDO parentApp = baseMapper.selectById(appDO.getParentId());
                appDO.setPath(String.join(",", parentApp.getPath(), appDO.getId().toString()));
            } else {
                appDO.setPath(appDO.getId().toString());
            }
            baseMapper.insert(appDO);
            app.setId(appDO.getId());
        }
        // todo 此处的持久化需要再进行优化一下
        //saveMenu(app);
    }

    @Override
    public List<App> findAll() {
        List<AppDO> appDOS = baseMapper.selectList(Wrappers.emptyWrapper());
        List<App> apps = appDOS.stream()
                .map(baseDataConverter::fromData)
                .toList();
        return CatTreeNode.merge(apps);
    }

    @Override
    public App findById(Long id) {
        AppDO appDO = baseMapper.selectById(id);
        if (appDO == null) {
            return null;
        }
        App app = baseDataConverter.fromData(appDO);
        if (appDO.getParentId() != null) {
            AppDO parentApp = baseMapper.selectById(appDO.getParentId());
            app.setParentApp(baseDataConverter.fromData(parentApp));
        }
        compileMenu(Collections.singleton(app));
        return app;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ApiPage<App> page(AppQuery query) {
        LambdaQueryWrapper<AppDO> queryWrapper = Wrappers.<AppDO>lambdaQuery();
        if (StringUtils.hasText(query.getName())) {
            queryWrapper.like(AppDO::getName, query.getName());
        }
        if (query.getActiveStatus() != null) {
            queryWrapper.eq(AppDO::getActiveStatus, query.getActiveStatus());
        }
        IPage<AppDO> page = baseMapper.selectPage(query.getIpage(), queryWrapper);
        return IPageUtils.toApiPage(page, (iPage) -> {
            List<AppDO> records = (List<AppDO>) iPage.getRecords();
            // 这是所有被检索出来的节点。但需要补充这个节点上的所有数据内容，然后再构建树形
            List<Long> ids = records.stream().filter(app -> StringUtils.hasText(app.getPath())).map(app -> {
                Splitter splitter = Splitter.on(",").omitEmptyStrings().trimResults();
                return splitter.splitToList(app.getPath()).stream().map(Long::parseLong).toList();
            }).flatMap(Collection::stream).toList();
            if (CollectionUtils.isEmpty(ids)) {
                return new ArrayList<>();
            }
            List<AppDO> result = baseMapper.selectBatchIds(ids);
            List<App> apps = result.stream()
                    .map(baseDataConverter::fromData)
                    .sorted(Comparator.comparing(App::getCreateTime))
                    .toList();
            return CatTreeNode.merge(apps);
        });
    }

    @Override
    public List<App> findByParentId(Long parentId) {
        LambdaQueryWrapper<AppDO> queryWrapper = Wrappers.<AppDO>lambdaQuery();
        queryWrapper.eq(AppDO::getParentId, parentId);
        List<AppDO> all = baseMapper.selectList(queryWrapper);
        return all.stream()
                .map(baseDataConverter::fromData)
                .collect(Collectors.toList());
    }

    @Override
    public void onlyUpdateAppMenu(App app) {
        saveMenu(app);
    }

    @Override
    public List<App> listApp(Long tenantId, AppQuery query) {
        AppDO queryDO = baseDataConverter.toData(query);
        List<AppDO> appDOS = baseMapper.selectList(Wrappers.<AppDO>lambdaQuery()
                .setEntity(queryDO));
        return baseDataConverter.fromData(appDOS);
    }

    @Override
    public List<App> listAppByIds(Long tenantId, List<Long> ids) {
        List<AppDO> appDOS = baseMapper.selectBatchIds(ids);
        List<App> apps = baseDataConverter.fromData(appDOS);
        compileMenu(apps);
        return apps;
    }

    private void compileMenu(Collection<App> apps) {
        if (CollectionUtils.isEmpty(apps)) {
            return;
        }
        List<Long> ids = apps.stream().map(App::getId).toList();
        List<AppMenuDO> appMenus = appMenuDOMapper.selectList(Wrappers.<AppMenuDO>lambdaQuery().
                in(AppMenuDO::getAppId, ids));
        if (CollectionUtils.isEmpty(appMenus)) {
            return;
        }
        List<Long> menuIds = appMenus.stream().map(AppMenuDO::getMenuId).toList();
        List<Menu> menus = menuRepository.findByIds(menuIds);
        if (CollectionUtils.isEmpty(menus)) {
            return;
        }
        Map<Long, List<AppMenuDO>> appMenuMap = appMenus.stream()
                .collect(Collectors.groupingBy(AppMenuDO::getAppId));
        Map<Long, Menu> menuMap = menus.stream()
                .collect(Collectors.toMap(Menu::getId, Function.identity()));
        apps.forEach(app -> {
            List<AppMenuDO> appMenuList = appMenuMap.get(app.getId());
            if (CollectionUtils.isNotEmpty(appMenuList)) {
                List<Menu> menuList = appMenuList.stream()
                        .map(m -> menuMap.get(m.getMenuId())).toList();
                app.setMenus(CatTreeNode.merge(menuList));
            }
        });
    }

    private void saveMenu(App app) {
        List<Menu> menus = app.getMenus();
        if (CollectionUtils.isEmpty(menus)) {
            // 表示删掉掉所有的
            appMenuDOMapper.delete(Wrappers.<AppMenuDO>lambdaQuery()
                    .eq(AppMenuDO::getAppId, app.getId()));
            return;
        }
        List<AppMenuDO> appMenus = menus.stream()
                .map(m -> {
                    AppMenuDO appMenuDO = new AppMenuDO();
                    appMenuDO.setAppId(app.getId());
                    appMenuDO.setMenuId(m.getId());
                    return appMenuDO;
                }).toList();
        // 保留添加，痕迹，所以逻辑删除的，不去恢复它
        List<AppMenuDO> appMenuDOS = appMenuDOMapper.selectList(Wrappers.<AppMenuDO>lambdaQuery()
                .eq(AppMenuDO::getAppId, app.getId()));
        Set<Long> menuIdSet = new HashSet<>();
        Set<Long> menuIdSetTemp = appMenuDOS.stream()
                .map(AppMenuDO::getMenuId)
                .collect(Collectors.toSet());
        if (CollectionUtils.isNotEmpty(menuIdSetTemp)) {
            menuIdSet.addAll(menuIdSetTemp);
        }

        appMenus.forEach(am -> {
            Long menuId = am.getMenuId();
            if (menuIdSet.contains(am.getMenuId())) {
                // 已经存在这个关系了,保持原样
                menuIdSet.remove(menuId);
            } else {
                // 没有，需要新增
                am.setId(idService.nextId());
                am.setCreateBy(AuthUtil.getLoginId());
                am.setCreateTime(LocalDateTime.now());
                appMenuDOMapper.insert(am);
            }
        });
        // 剩下的 menuIdSet 的是需要删除的
        if (CollectionUtils.isNotEmpty(menuIdSet)) {
            appMenuDOMapper.delete(Wrappers.<AppMenuDO>lambdaQuery()
                    .eq(AppMenuDO::getAppId, app.getId())
                    .in(AppMenuDO::getMenuId, menuIdSet));
        }
    }

}
