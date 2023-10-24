package cn.catguild.system.infrastructure.repository;

import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.CatTreeNode;
import cn.catguild.common.utility.CollectionUtils;
import cn.catguild.common.utility.IPageUtils;
import cn.catguild.common.utility.StringUtils;
import cn.catguild.system.domain.App;
import cn.catguild.system.domain.repositroy.AppRepository;
import cn.catguild.system.infrastructure.converter.AppDataConverter;
import cn.catguild.system.infrastructure.domain.AppDO;
import cn.catguild.system.infrastructure.domain.query.AppQuery;
import cn.catguild.system.infrastructure.idgeneration.IdGenerationService;
import cn.catguild.system.infrastructure.repository.mapper.AppDOMapper;
import cn.catguild.system.util.AuthUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.base.Splitter;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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

    private final AppDataConverter baseDataConverter;

    private final IdGenerationService idService;

    @Override
    public void save(App app) {
        Long loginId = AuthUtil.getLoginId();
        if (app.getId() != null && app.getId() > 0) {
            // update
            AppDO appDO = baseDataConverter.toData(app);
            appDO.setLmBy(loginId);
            appDO.setLmTime(LocalDateTime.now());
            baseMapper.updateById(appDO);
        } else {
            // insert
            AppDO appDO = baseDataConverter.toData(app);
            appDO.setId(Long.valueOf(idService.nextUID()));
            appDO.setCBy(loginId);
            appDO.setCTime(LocalDateTime.now());
            // 构建路径
            if (appDO.getParentId() == null) {
                appDO.setParentId(0L);
            }
            if (appDO.getParentId() != 0L) {
                AppDO parentApp = baseMapper.selectById(appDO.getParentId());
                appDO.setPath(String.join(",", parentApp.getPath(), appDO.getId().toString()));
            }else {
                appDO.setPath(appDO.getId().toString());
            }
            baseMapper.insert(appDO);
            app.setId(appDO.getId());
        }
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
        return app;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ApiPage<App> page(AppQuery query) {
        LambdaQueryWrapper<AppDO> queryWrapper = Wrappers.<AppDO>lambdaQuery();
        if (StringUtils.hasText(query.getName())) {
            queryWrapper.like(AppDO::getName, query.getName());
        }
        if (query.getActiveStatus() != null){
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
            if (CollectionUtils.isEmpty(ids)){
                return new ArrayList<>();
            }
            List<AppDO> result = baseMapper.selectBatchIds(ids);
            List<App> apps = result.stream()
                    .map(baseDataConverter::fromData)
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

}
