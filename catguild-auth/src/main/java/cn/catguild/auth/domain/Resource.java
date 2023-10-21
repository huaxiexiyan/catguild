package cn.catguild.auth.domain;

import lombok.Data;

/**
 * @author xiyan
 * @date 2023/8/22 15:21
 */
@Data
public class Resource {

    private Long id;

    /**
     * 资源名
     */
    private String name;

    /**
     * 实体id
     */
    private Long refId;

    /**
     * 类型
     */
    private String refType;

    /**
     * 什么应用产生的
     */
    private Long appId;

}
