package cn.catguild.common.type.auth;

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
     * 不受权限系统管制
     */
    SUPER_ADMINISTRATOR,

    /**
     * APP里的超级管理员，只有一个并且本身是不可被修改
     * 不受权限系统管制（在租户范围内，单个app内）
     */
    APP_ADMINISTRATOR,

    /**
     * 普通用户
     * 无特殊权限，具体权限管理交由权限系统分配验证
     */
    ORDINARY_USERS

}
