package cn.catguild.user.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiyan
 * @date 2022-08-07 10:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtCredentials {

	private String token;
}
