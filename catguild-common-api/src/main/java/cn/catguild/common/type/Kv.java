package cn.catguild.common.type;

import lombok.Data;

/**
 * @author xiyan
 * @date 2022-04-26 23:04
 */
@Data
public class Kv<T, U> {
	private T k;
	private U v;
}
