package cn.catguild.common.api;

import cn.catguild.common.entity.CatPage;
import lombok.Data;

import java.util.Collection;

/**
 * @author xiyan
 * @date 2022-04-03 13:55
 */
@Data
public class ApiPage<T> {

	private Collection<T> list;

	private long current;

	private long size;

	private long total;

	public ApiPage() {

	}

	public ApiPage(CatPage<?> search) {
		this.current = search.getCurrent() < 0 ? 0 : search.getCurrent();
		this.size = search.getSize() < 0 ? 0 : search.getSize();
		this.total = search.getTotal() < 0 ? 0 : search.getTotal();
	}

}
