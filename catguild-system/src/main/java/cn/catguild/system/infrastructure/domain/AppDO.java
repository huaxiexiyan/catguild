package cn.catguild.system.infrastructure.domain;

import cn.catguild.common.entity.jpa.AbstractEntity;
import cn.catguild.common.type.ActiveStatus;
import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用表
 *
 * @author xiyan
 * @date 2023/8/22 15:11
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("system_app")
public class AppDO extends AbstractEntity {

    /**
     * 上层应用id,不允许更新
     */
    @TableField(updateStrategy = FieldStrategy.NEVER)
    private Long parentId;

    /**
     * id路径(包含自己)
     */
    private String path;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 描述
     */
    private String describe;

    /**
     * 活跃状态
     */
    private ActiveStatus activeStatus;


}
