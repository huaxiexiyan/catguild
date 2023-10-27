package cn.catguild.auth.infrastructure.repository.entity;

import cn.catguild.common.entity.jpa.BaseTenant;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/10/19 15:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("auth_resource")
public class ResourceDO extends BaseTenant {

    /**
     * 什么应用产生的
     */
    private Long appId;

    /**
     * 实体id
     */
    private Long refId;

    /**
     * 类型
     */
    private String refType;

}
