package cn.catguild.user.utility;

import cn.catguild.common.api.ApiPage;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.Collection;

/**
 * @author xiyan
 * @date 2022/10/5 13:37
 */
public class IPageUtils {

	public static <T> Page<T> toIPage(ApiPage<T> apiPage) {
		return new Page<>(apiPage.getCurrent(), apiPage.getSize());
	}

	public static <T> Page<T> toIPage(Page<T> targetPage, ApiPage<?> apiPage) {
		targetPage.setCurrent(apiPage.getCurrent());
		targetPage.setSize(apiPage.getSize());
		return targetPage;
	}

	public static <T> ApiPage<T> toApiPage(IPage<T> page) {
		ApiPage<T> apiPage = new ApiPage<>();
		apiPage.setCurrent(page.getCurrent());
		apiPage.setSize(page.getSize());
		apiPage.setTotal(page.getTotal());
		apiPage.setList(page.getRecords());
		return apiPage;
	}

	public static <T> ApiPage<T> toApiPage(ApiPage<T> targetApiPage, Page<?> accountDOPage) {
		targetApiPage.setCurrent(accountDOPage.getCurrent());
		targetApiPage.setSize(accountDOPage.getSize());
		targetApiPage.setTotal(accountDOPage.getTotal());
		targetApiPage.setList((Collection<T>) accountDOPage.getRecords());
		return targetApiPage;
	}

}
