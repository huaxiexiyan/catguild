package cn.catguild.common.entity.jpa;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import jakarta.persistence.Column;
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
public abstract class AbstractEntity {

    @Comment("记录主键")
    @Id
    @JsonDeserialize(as = Long.class)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 创建时间
     **/
    @Comment("记录创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(nullable = false)
    private LocalDateTime cTime;

    @Comment("记录创建人id")
    @JsonIgnore
    @Column(nullable = false)
    private Long cBy;

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

    @Comment("记录逻辑删除人id")
    @JsonIgnore
    private Long deBy;

}
