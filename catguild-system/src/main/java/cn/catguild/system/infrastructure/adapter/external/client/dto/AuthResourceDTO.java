package cn.catguild.system.infrastructure.adapter.external.client.dto;

import lombok.Data;

/**
 * @author xiyan
 * @date 2023/10/19 13:43
 */
@Data
public class AuthResourceDTO {

    /**
     * 授权id
     */
    private Long authId;

    /**
     * 资源id
     */
    private Long resourceId;

    /**
     * 资源类型 -> tableName
     */
    private String resourceType;

}
