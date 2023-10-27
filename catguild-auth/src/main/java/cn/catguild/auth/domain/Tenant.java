package cn.catguild.auth.domain;

import cn.catguild.common.domain.BaseBO;
import cn.catguild.common.type.ActiveStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 租户（以租户为界隔离数据及权限）
 *
 * @author xiyan
 * @date 2023/7/31 15:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Tenant extends BaseBO {

    /**
     * 唯一UID
     */
    private Integer uid;

    /**
     * 租户名
     */
    private String name;

    /**
     * 注册邮箱
     */
    private String email;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 绑定的域名，多个逗号风格
     */
    private String domainName;

    /**
     * 活跃状态
     */
    private ActiveStatus activeStatus;

    private List<Long> appIds;

}
