package cn.catguild.common.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.Collections;

/**
 * @author xiyan
 * @date 2022-04-04 23:32
 */
@Getter
public class CatPage<T> {

	/**
	 * 查询数据列表
	 */
	@Setter
	private Collection<T> list;

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


	public CatPage(CatPage<?> page) {
		this.total = page.getTotal();
		this.size = page.getSize();
		this.current = page.getCurrent();
		this.pages = page.getPages();
		this.list = Collections.emptyList();
	}

	public CatPage(CatPageQuery<?> pageQuery) {
		this.total = pageQuery.getTotal();
		this.size = pageQuery.getSize();
		this.current = pageQuery.getCurrent();
		this.pages = pageQuery.getPages();
		this.list = Collections.emptyList();
	}

	public CatPage(long current, long size, long total) {
		this.total = total;
		this.size = size;
		this.current = current;
		this.list = Collections.emptyList();
	}

}
