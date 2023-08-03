package cn.catguild.auth.domain.common;

import lombok.Data;

/**
 * @author xiyan
 * @date 2023/7/31 17:26
 */
@Data
public abstract class AbstractEntity {

    /**
     * 创建时间
     **/
    private Long createdTime;

    private Long createdBy;

    /**
     * 最后修改时间
     **/
    private Long lastModifiedTime;

    private Long lastModifiedBy;

    /**
     * 删除时间
     */
    private Long deletedTime;

    private Long deletedBy;

}
