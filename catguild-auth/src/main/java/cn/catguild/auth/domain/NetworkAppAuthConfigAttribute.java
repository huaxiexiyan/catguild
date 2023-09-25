package cn.catguild.auth.domain;

import cn.catguild.common.entity.jpa.BaseTenantSub;
import cn.catguild.common.type.AttributeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * @author xiyan
 * @date 2023/9/19 11:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`auth_network_app_auth_config_attribute`")
public class NetworkAppAuthConfigAttribute extends BaseTenantSub {

    @Comment("关联的主表id")
    private Long networkAppAuthConfigId;

    @Comment("属性名")
    private String attributeName;

    @Comment("属性值")
    private String attributeValue;

    @Comment("属性值类型")
    private AttributeType attributeType;

}
