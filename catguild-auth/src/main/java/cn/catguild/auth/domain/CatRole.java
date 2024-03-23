package cn.catguild.auth.domain;

import cn.catguild.common.type.ActiveStatus;
import lombok.Data;

/**
 * @author xiyan
 * @date 2023/10/8 14:03
 */
@Data
public class CatRole  {

    private Long id;

    /**
     * 应用id
     */
    private Long appId;

	private String appCode;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色名唯一code
     */
    private String code;

    /**
     * 活跃状态
     */
    private ActiveStatus activeStatus;

}
