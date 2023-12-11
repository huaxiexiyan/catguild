package cn.catguild.business.automatic.infrastructure.domain.entity;

import cn.catguild.common.entity.jpa.BaseTenant;
import cn.catguild.common.type.ActiveStatus;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 授权类型
 *
 * @author xiyan
 * @date 2023/10/20 14:25
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("business_automatic_app_auth_type")
public class AppAuthTypeDO extends BaseTenant {

    private String name;

    /**
     * code码预设分配，不可修改
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private String code;

    private ActiveStatus activeStatus;

    private LocalDateTime cTime;

}
