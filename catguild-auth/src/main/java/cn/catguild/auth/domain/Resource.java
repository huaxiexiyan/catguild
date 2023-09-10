package cn.catguild.auth.domain;

import cn.catguild.auth.domain.type.ResourceType;
import cn.catguild.common.entity.jpa.AbstractEntity;
import cn.catguild.common.type.ActiveStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/8/22 15:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_resource`")
public class Resource extends AbstractEntity {

    @Comment("资源名")
    @Column(length = 255)
    private String name;

    @Comment("指向关联的id")
    private Long refId;

    @Comment("资源类型:菜单、按钮")
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    @Enumerated(EnumType.STRING)
    private ActiveStatus status;

}
