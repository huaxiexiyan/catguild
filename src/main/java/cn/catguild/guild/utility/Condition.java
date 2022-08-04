package cn.catguild.guild.utility;

import cn.catguild.guild.domain.common.CatPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author xiyan
 * @date 2022-04-03 23:33
 */
public class Condition {
	private Condition() {
	}

	public static <T> IPage<T> getIPage(CatPage<?> page) {
		return new Page<>(page.getCurrent(), page.getSize(), page.getTotal());
	}

	public static <T> CatPage<T> getCatPage(IPage<?> iPage) {
		return new CatPage<T>(0, 0, 0);
	}
}
