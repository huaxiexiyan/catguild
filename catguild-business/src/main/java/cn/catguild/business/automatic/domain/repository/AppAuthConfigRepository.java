package cn.catguild.business.automatic.domain.repository;

import cn.catguild.business.automatic.domain.AppAuthConfig;
import cn.catguild.business.automatic.infrastructure.domain.query.AppAuthConfigQuery;
import cn.catguild.common.api.ApiPage;

/**
 * @author xiyan
 * @date 2023/10/10 21:23
 */
public interface AppAuthConfigRepository {

    ApiPage<AppAuthConfig> page(Long tenantId, AppAuthConfigQuery query);

    void save(Long tenantId, AppAuthConfig appAuthConfig);


}
