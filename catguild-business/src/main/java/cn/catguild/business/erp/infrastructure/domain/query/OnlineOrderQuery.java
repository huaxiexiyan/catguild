package cn.catguild.business.erp.infrastructure.domain.query;

import cn.catguild.business.erp.infrastructure.domain.OnlineOrderDO;
import cn.catguild.common.entity.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/10/16 11:50
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class OnlineOrderQuery extends BasePageQuery<OnlineOrderDO> {

    private String likeOrderNum;

}
