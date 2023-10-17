package cn.catguild.business.erp.infrastructure.domain.query;

import cn.catguild.business.erp.infrastructure.domain.OnlineOrderDO;
import cn.catguild.common.entity.BaseQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/10/16 11:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OnlineOrderQuery extends BaseQuery<OnlineOrderDO> {

    private String likeOrderNum;

}
