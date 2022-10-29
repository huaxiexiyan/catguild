package cn.catguild.common.entity;

import lombok.Data;

/**
 * @author xiyan
 * @date 2022/10/5 13:35
 */
@Data
public class BaseQuery {

	/**
	 * 总数
	 */
	private Long total;
	/**
	 * 每页显示条数，默认 10
	 */
	private Long size;
	/**
	 * 当前页
	 */
	private Long current;
	/**
	 * 当前分页总页数
	 */
	private Long pages;

}
