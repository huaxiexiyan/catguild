package cn.catguild.common.entity.jpa;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
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
    @JsonDeserialize(as = Long.class)
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Long tenantId;

}
