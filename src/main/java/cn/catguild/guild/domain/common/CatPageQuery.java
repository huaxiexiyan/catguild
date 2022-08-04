package cn.catguild.guild.domain.common;

import lombok.Data;
import lombok.Setter;

/**
 * @author xiyan
 * @date 2022-04-04 23:34
 */
@Data
public class CatPageQuery<T> {

	/**
	 * 总数
	 */
	@Setter
	private long total = 0;
	/**
	 * 每页显示条数，默认 10
	 */
	private long size = 10;
	/**
	 * 当前页
	 */
	private long current = 1;
	/**
	 * 当前分页总页数
	 */
	private long pages = 1;

}
