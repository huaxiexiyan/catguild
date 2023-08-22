package cn.catguild.auth.domain;

import cn.catguild.common.entity.jpa.AbstractEntity;
import cn.catguild.common.type.ActiveStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * 应用版本
 *
 * @author xiyan
 * @date 2023/8/22 15:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_app_version`")
public class AppVersion extends AbstractEntity {

    @Comment("应用id")
    private Long appId;

    @Comment("从该版本id继承")
    private Long parentId;

    @Comment("版本名称")
    @Column(length = 50)
    private String name;

    @Comment("版本号")
    @Column(length = 50)
    private Integer uid;

    @Comment("活跃状态")
    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

}
