package cn.catguild.business.automatic.infrastructure.repository;

import cn.catguild.business.automatic.domain.AppAuthConfig;
import cn.catguild.business.automatic.domain.repository.AppAuthConfigRepository;
import cn.catguild.business.automatic.infrastructure.converter.AppAuthConfigDataConverter;
import cn.catguild.business.automatic.infrastructure.domain.custom.AppAuthConfigCustom;
import cn.catguild.business.automatic.infrastructure.domain.entity.AppAuthConfigDO;
import cn.catguild.business.automatic.infrastructure.domain.query.AppAuthConfigQuery;
import cn.catguild.business.automatic.infrastructure.repository.mapper.AppAuthConfigDOMapper;
import cn.catguild.business.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.business.util.AuthUtil;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.exception.BizException;
import cn.catguild.common.utility.IPageUtils;
import cn.catguild.common.utility.StringUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/20 15:21
 */
@Slf4j
@AllArgsConstructor
@Repository
public class AppAuthConfigRepositoryImpl implements AppAuthConfigRepository {

    private final AppAuthConfigDOMapper baseMapper;

    private final AppAuthConfigDataConverter baseDataConverter;

    private final IdGenerationClient idClient;

    @Override
    @SuppressWarnings("unchecked")
    public ApiPage<AppAuthConfig> page(Long tenantId, AppAuthConfigQuery query) {
        IPage<AppAuthConfigCustom> page = baseMapper.selectCustomPage(tenantId, query.getIpage(), query);
        return IPageUtils.toApiPage(page, (iPage) -> {
            List<AppAuthConfigCustom> records = (List<AppAuthConfigCustom>) iPage.getRecords();
            return records.stream()
                    .map(baseDataConverter::fromData)
                    .toList();
        });
    }

    @Override
    public void save(Long tenantId, AppAuthConfig appAuthConfig) {
        AppAuthConfigDO data = baseDataConverter.toData(appAuthConfig);
        data.setTenantId(tenantId);
        LocalDateTime now = LocalDateTime.now();
        if (StringUtils.hasText(data.getAccessToken())) {
            data.setLastUpdateAccessTokenTime(now);
        }
        if (StringUtils.hasText(data.getRefreshToken())) {
            data.setLastUpdateRefreshTokenTime(now);
        }

        if (appAuthConfig.getId() == null) {
            // 新增
            boolean exists = baseMapper.exists(Wrappers.<AppAuthConfigDO>lambdaQuery()
                    .eq(AppAuthConfigDO::getTenantId, tenantId)
                    .eq(AppAuthConfigDO::getAppAuthTypeId, appAuthConfig.getAppAuthType().getId())
                    .eq(AppAuthConfigDO::getCBy, AuthUtil.getLoginId())
            );
            if (exists) {
                throw new BizException("授权类型重复");
            }
            data.setId(idClient.nextId());
            data.setCBy(AuthUtil.getLoginId());
            data.setCTime(now);
            baseMapper.insert(data);
        } else {
            // 更新
            data.setLmBy(AuthUtil.getLoginId());
            data.setLmTime(now);
            baseMapper.updateById(data);
        }
    }

    @Override
    public void remove(Long tenantId, List<Long> ids) {
        ids.forEach(id -> remove(tenantId, id));
    }

    @Override
    public void remove(Long tenantId, Long id) {
        baseMapper.delete(Wrappers.<AppAuthConfigDO>lambdaQuery()
                .eq(AppAuthConfigDO::getTenantId, tenantId)
                .eq(AppAuthConfigDO::getId, id));
    }

}
