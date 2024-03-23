package cn.catguild.auth.application.command;

import cn.catguild.auth.infrastructure.repository.domain.entity.MenuDO;
import cn.catguild.common.entity.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author xiyan
 * @date 2024/1/17 17:39
 */
@ToString
@EqualsAndHashCode(callSuper = true)
@Data
public class RolePageCommand  extends BasePageQuery<MenuDO> {
}
