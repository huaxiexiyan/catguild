package cn.catguild.business.automatic.application;

import cn.catguild.business.automatic.domain.AppAuthConfig;
import cn.catguild.business.automatic.domain.repository.AppAuthConfigRepository;
import cn.catguild.business.automatic.infrastructure.domain.query.AppAuthConfigQuery;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.type.ActiveStatus;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/10 21:25
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class AppAuthConfigApplication {


    private final AppAuthConfigRepository baseRepository;

    public ApiPage<AppAuthConfig>  page(Long tenantId, AppAuthConfigQuery query){
        return baseRepository.page(tenantId,query);
    }

    public void add(Long tenantId, AppAuthConfig appAuthConfig) {
        baseRepository.save(tenantId, appAuthConfig);
    }

    public void update(Long tenantId, Long id, AppAuthConfig appAuthConfig) {
        appAuthConfig.setId(id);
        baseRepository.save(tenantId, appAuthConfig);
    }

    public void updateActiveStatus(Long tenantId, Long id, ActiveStatus activeStatus) {
        AppAuthConfig appAuthConfig = new AppAuthConfig();
        appAuthConfig.setId(id);
        appAuthConfig.setActiveStatus(activeStatus);
        baseRepository.save(tenantId, appAuthConfig);
    }

    public void clearToken(Long tenantId, List<Long> ids) {
        baseRepository.remove(tenantId, ids);
    }

}
