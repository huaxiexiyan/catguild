package cn.catguild.common.type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiyan
 * @date 2022-04-26 23:04
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Kv<T, U> {
	private T k;
	private U v;
}
