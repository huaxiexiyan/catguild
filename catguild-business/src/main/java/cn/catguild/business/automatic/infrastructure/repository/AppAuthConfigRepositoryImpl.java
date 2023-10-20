package cn.catguild.business.automatic.infrastructure.repository;

import cn.catguild.business.automatic.domain.AppAuthConfig;
import cn.catguild.business.automatic.domain.repository.AppAuthConfigRepository;
import cn.catguild.business.automatic.infrastructure.converter.AppAuthConfigDataConverter;
import cn.catguild.business.automatic.infrastructure.domain.custom.AppAuthConfigCustom;
import cn.catguild.business.automatic.infrastructure.domain.query.AppAuthConfigQuery;
import cn.catguild.business.automatic.infrastructure.repository.mapper.AppAuthConfigDOMapper;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.utility.IPageUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

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

    @Override
    @SuppressWarnings("unchecked")
    public ApiPage<AppAuthConfig> page(Long tenantId, AppAuthConfigQuery query) {
        IPage<AppAuthConfigCustom> page = baseMapper.selectCustomPage(tenantId, query.getIpage(), query);
        return IPageUtils.toApiPage(page, (iPage) -> {
            List<AppAuthConfigCustom> records = (List<AppAuthConfigCustom>)iPage.getRecords();
            return records.stream()
                    .map(baseDataConverter::fromData)
                    .toList();
        });
    }

}
