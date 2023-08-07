package cn.catguild.auth.presentation.model;

import cn.catguild.auth.domain.User;
import cn.catguild.common.api.ApiPage;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/8/7 11:32
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserQuery extends ApiPage<User> {
}
