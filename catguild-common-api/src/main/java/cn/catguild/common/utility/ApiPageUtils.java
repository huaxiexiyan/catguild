package cn.catguild.common.utility;

import cn.catguild.common.api.ApiPage;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Function;

/**
 * @author xiyan
 * @date 2024/1/18 11:26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiPageUtils {

	public static <R,T> ApiPage<T> convertApiPage(ApiPage<R> page, Function<ApiPage<R>, List<T>> conversionFunction) {
		ApiPage<T> apiPage = new ApiPage<>();
		apiPage.setCurrent(page.getCurrent());
		apiPage.setSize(page.getSize());
		apiPage.setTotal(page.getTotal());
		// 接受一个函数，进行 records 转换
		apiPage.setRecords(conversionFunction.apply(page));
		return apiPage;
	}

}
