package cn.catguild.system.infrastructure.id.domain;

import cn.catguild.common.entity.jpa.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/8/11 11:32
 */
//@EqualsAndHashCode(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`system_uid`")
public class Uid extends AbstractEntity {

    @Comment("uid")
    private Integer uid;

}
