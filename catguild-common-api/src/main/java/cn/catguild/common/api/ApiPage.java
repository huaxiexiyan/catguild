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

	private Collection<T> records;

	private long current = 1;

	private long size = 10;

	private long total = 0;

	public ApiPage() {

	}

	public ApiPage(CatPage<?> search) {
		this.current = search.getCurrent() < 0 ? 1 : search.getCurrent();
		this.size = search.getSize() < 0 ? 10 : search.getSize();
		this.total = search.getTotal() < 0 ? 0 : search.getTotal();
	}

}
