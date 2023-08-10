package cn.catguild.auth.domain.type;

import lombok.Getter;

/**
 * @author xiyan
 * @date 2023/8/9 14:02
 */
@Getter
public enum UserAuthorityType {

    /**
     * 超级管理员:
     * 只有一个并且本身是不可被修改
     * 不受权限系统管制（在租户层面上）
     */
    SUPER_ADMINISTRATOR,

    /**
     * 普通管理员
     * 可以有多个，其绑定的相关信息经过严格的验证才能就进行编辑操作
     * 并且其权限将可以被权限系统管制
     */
    ORDINARY_ADMINISTRATOR,

    /**
     * 普通用户
     * 无特殊权限，具体权限管理交由权限系统分配验证
     */
    ORDINARY_USERS

}
