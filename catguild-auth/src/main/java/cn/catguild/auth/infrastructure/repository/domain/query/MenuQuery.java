package cn.catguild.auth.infrastructure.repository.domain.query;

import cn.catguild.auth.infrastructure.repository.domain.entity.MenuDO;
import cn.catguild.common.entity.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/7/31 17:40
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MenuQuery extends BasePageQuery<MenuDO> {
}
