package cn.catguild.auth.controller.vo;

import lombok.Data;

/**
 * @author xiyan
 * @date 2023/5/13 19:19
 */
@Data
public class LoginVO {

    private Long userId;

    private String username;

    private String token;

    private String realName;

    private String desc;

}
