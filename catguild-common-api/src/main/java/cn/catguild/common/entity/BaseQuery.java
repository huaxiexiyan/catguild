package cn.catguild.common.entity;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

/**
 * @author xiyan
 * @date 2022/10/5 13:35
 */
@Data
public class BaseQuery<T> {

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

	public IPage<T> getIpage(){
		return new Page<T>(current,size,total);
	}

}
