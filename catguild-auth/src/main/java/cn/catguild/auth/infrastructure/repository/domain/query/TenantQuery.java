package cn.catguild.auth.infrastructure.repository.domain.query;

import cn.catguild.auth.infrastructure.repository.domain.entity.TenantDO;
import cn.catguild.common.entity.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/7/31 17:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TenantQuery extends BaseQuery<TenantDO> {
}
