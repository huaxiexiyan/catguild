package cn.catguild.user.domain.bo;

import lombok.Data;

/**
 * @author xiyan
 * @date 2023/4/9 13:45
 */
@Data
public class AuthUser {

	private Long catUserId;

	private String name;

	private String token;

}
