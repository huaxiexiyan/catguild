package cn.catguild.common.client;

import lombok.Data;
import lombok.ToString;

/**
 * @author xiyan
 * @date 2022/10/5 13:35
 */
@ToString
@Data
public class BaseQuery {

	/**
	 * 总数
	 */
	private Long total = 0L;
	/**
	 * 每页显示条数，默认 10
	 */
	private Long size = 10L;
	/**
	 * 当前页
	 */
	private Long current = 1L;
	/**
	 * 当前分页总页数
	 */
	private Long pages;


}
