package cn.catguild.common.entity.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;

/**
 * @author xiyan
 * @date 2023/7/31 17:26
 */
@MappedSuperclass
@Data
public abstract class AbstractEntitySub {

    @Comment("记录主键")
    @Id
    @JsonDeserialize(as = Long.class)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 最后修改时间
     **/
    @Comment("记录最后修改时间")
    @JsonIgnore
    private LocalDateTime lmTime;

    @Comment("记录最后修改人id")
    @JsonIgnore
    private Long lmBy;

    /**
     * 删除时间
     */
    @Comment("记录逻辑删除时间")
    @JsonIgnore
    private LocalDateTime deTime;


}
