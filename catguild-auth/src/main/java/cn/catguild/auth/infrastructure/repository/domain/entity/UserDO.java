package cn.catguild.auth.infrastructure.repository.domain.entity;

import cn.catguild.common.entity.jpa.BaseTenant;
import cn.catguild.common.type.ActiveStatus;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xiyan
 * @since 2023-11-05
 */
@Getter
@Setter
@TableName("auth_user")
public class UserDO extends BaseTenant {

    /**
     * 用户名
     */
    private String name;

    private String authorityType;

    private ActiveStatus status;

}
