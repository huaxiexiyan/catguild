package cn.catguild.auth.repository.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author xiyan
 * @date 2023/5/4 11:53
 */
@Data
public class BaseDO {

    @TableId(type= IdType.INPUT)
    private Long id;

    private Long createdTime;

    private Long createdBy;

    private Long updatedTime;

    private Long updatedBy;

    private Long deletedTime;

    private Long deletedBy;

}
