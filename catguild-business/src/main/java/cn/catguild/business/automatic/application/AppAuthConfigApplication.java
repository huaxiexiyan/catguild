package cn.catguild.business.automatic.application;

import cn.catguild.business.automatic.domain.AppAuthConfig;
import cn.catguild.business.automatic.domain.repository.AppAuthConfigRepository;
import cn.catguild.business.automatic.infrastructure.domain.query.AppAuthConfigQuery;
import cn.catguild.common.api.ApiPage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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

}
