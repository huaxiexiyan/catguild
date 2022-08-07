package cn.catguild.user.domain.auth;

import cn.catguild.user.domain.entity.CatUser;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Lionel
 * @date 2022-08-07 13:49
 */
@Accessors(chain = true)
@Data
public class JwtPayload {

	private CatUser catUser;

	/**
	 * （Issuer）：签发人
	 */
	private String iss;
	/**
	 * （Expiration Time）：令牌过期时间。 毫秒数
	 */
	private Long exp;
	/**
	 * （Subject）：主题。
	 */
	private String sub;
	/**
	 * （Audience）：令牌受众。
	 */
	private String aud;
	/**
	 * （Not Before）：令牌生效时间。 时间戳
	 */
	private Long nbf;
	/**
	 * （（Issued At）：令牌签发时间。 时间戳
	 */
	private Long iat;
	/**
	 * （JWT ID）：令牌编号
	 */
	private String jti;

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>(8);
		map.put("catUser",this.catUser);
		map.put("iss",this.iss);
		map.put("exp",this.exp);
		map.put("sub",this.sub);
		map.put("aud",this.aud);
		map.put("nbf",this.nbf);
		map.put("iat",this.iat);
		map.put("jti",this.jti);
		return map;
	}

}
