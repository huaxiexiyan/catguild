package cn.catguild.common.utility;

import cn.catguild.common.api.ApiPage;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.function.Function;

/**
 * @author xiyan
 * @date 2022/10/5 13:37
 */
public class IPageUtils {

	public static <T> IPage<T> toIPage(IPage<T> targetPage, ApiPage<?> apiPage) {
		targetPage.setCurrent(apiPage.getCurrent());
		targetPage.setSize(apiPage.getSize());
		return targetPage;
	}

	public static <R, T> ApiPage<T> toApiPage(IPage<R> page, Function<IPage<R>, List<T>> conversionFunction) {
		ApiPage<T> apiPage = new ApiPage<>();
		apiPage.setCurrent(page.getCurrent());
		apiPage.setSize(page.getSize());
		apiPage.setTotal(page.getTotal());
		// 接受一个函数，进行 records 转换
		apiPage.setRecords(conversionFunction.apply(page));
		return apiPage;
	}

}
