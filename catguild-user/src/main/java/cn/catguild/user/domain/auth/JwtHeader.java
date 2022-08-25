package cn.catguild.user.domain.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiyan
 * @date 2022-08-07 13:49
 */
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtHeader {

	/**
	 * 算法名
	 */
	private String alg;
	/**
	 * 类型
	 */
	private String typ;

	public Map<String, Object> toMap() {
		Map<String, Object> map = new HashMap<>(2);
		map.put("alg",this.alg);
		map.put("typ",this.typ);
		return map;
	}
}
