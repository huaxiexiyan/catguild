package cn.catguild.business.automatic.infrastructure.domain.query;

import cn.catguild.business.automatic.infrastructure.domain.entity.AppAuthConfigDO;
import cn.catguild.business.automatic.infrastructure.domain.type.AppAuthConfigStatus;
import cn.catguild.common.entity.BasePageQuery;
import cn.catguild.common.type.ActiveStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/10/16 11:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppAuthConfigQuery extends BasePageQuery<AppAuthConfigDO> {

    private String name;

    private ActiveStatus activeStatus;

    private AppAuthConfigStatus status;

}
