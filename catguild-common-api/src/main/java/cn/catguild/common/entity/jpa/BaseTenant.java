package cn.catguild.common.entity.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/7/31 17:22
 */
@MappedSuperclass
@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseTenant extends AbstractEntity {

    @Comment("租户id")
    @Column(nullable = false)
    private Long tenantId;

}
