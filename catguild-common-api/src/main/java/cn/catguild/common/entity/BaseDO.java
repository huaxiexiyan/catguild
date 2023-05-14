package cn.catguild.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author xiyan
 * @date 2023/5/14 22:57
 */
@Data
public class BaseDO {

    @TableId(type = IdType.INPUT)
    private Long id;

    private Long createdTime;

    private Long lastModifiedTime;

}
