package cn.catguild.auth.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/7/31 17:22
 */
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseTenant extends AbstractEntity{

    @Comment("租户id")
    private Long tenantId;

}
