package cn.catguild.auth.infrastructure.repository.domain.entity;

import cn.catguild.common.entity.jpa.BaseTenant;
import cn.catguild.common.type.ActiveStatus;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/10/19 15:53
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("auth_role")
public class CatRoleDO extends BaseTenant {

    /**
     * 应用id
     */
    private Long appId;

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
