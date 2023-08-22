package cn.catguild.auth.domain;

import cn.catguild.common.entity.jpa.AbstractEntity;
import cn.catguild.common.type.ActiveStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * 应用表
 *
 * @author xiyan
 * @date 2023/8/22 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_app`")
public class App extends AbstractEntity {

    @Comment("应用名称")
    @Column(length = 50)
    private String name;

    @Comment("活跃状态")
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

}
