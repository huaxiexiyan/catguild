package cn.catguild.system.infrastructure.domain.query;

import cn.catguild.common.entity.BasePageQuery;
import cn.catguild.common.type.ActiveStatus;
import cn.catguild.system.infrastructure.domain.AppDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/10/16 11:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppQuery extends BasePageQuery<AppDO> {

    private String name;

    private ActiveStatus activeStatus;

}
