package cn.catguild.auth.domain;

import cn.catguild.common.entity.jpa.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/8/22 15:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_app_resource`")
public class AppResource extends AbstractEntity {

    @Comment("应用版本id")
    private Long appVersionId;

    @Comment("资源id")
    private Long resourceId;

}
