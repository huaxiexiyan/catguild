package cn.catguild.auth.presentation.model;

import cn.catguild.auth.infrastructure.repository.domain.entity.UserDO;
import cn.catguild.common.entity.BasePageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/8/7 11:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends BasePageQuery<UserDO> {

    private String name;

}
