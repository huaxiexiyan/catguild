package cn.catguild.auth.presentation.model;

import cn.catguild.auth.domain.Tenant;
import cn.catguild.common.api.ApiPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/7/31 17:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class TenantQuery extends ApiPage<Tenant> {
}
