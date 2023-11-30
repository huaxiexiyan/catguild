package cn.catguild.auth.api.dto;

import lombok.Data;

/**
 * @author xiyan
 * @date 2023/11/24 16:07
 */
@Data
public class LoginDTO {
    //请求参数
    private String grantType;

    private Long tenantId;

    private String username;

    private String password;

    // 响应参数
    private String accessToken;

    private String tokenType;

    private Integer expiresIn;

}
