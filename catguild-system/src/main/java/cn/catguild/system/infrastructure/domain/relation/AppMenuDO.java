package cn.catguild.system.infrastructure.domain.relation;

import cn.catguild.common.entity.jpa.AbstractEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2023/10/22 12:05
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("system_app_menu")
public class AppMenuDO extends AbstractEntity {

    private Long appId;

    private Long menuId;

}
