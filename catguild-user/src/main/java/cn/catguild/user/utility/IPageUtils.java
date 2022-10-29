package cn.catguild.user.utility;

import cn.catguild.common.api.ApiPage;
import cn.catguild.common.entity.BaseQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author xiyan
 * @date 2022/10/5 13:37
 */
public class IPageUtils {

	public static <T> IPage<T> getIPage(BaseQuery query) {
		return new Page<>(query.getCurrent(), query.getSize());
	}

	public static <T> ApiPage<T> toApiPage(IPage<T> page) {
		ApiPage<T> apiPage = new ApiPage<>();
		apiPage.setCurrent(page.getCurrent());
		apiPage.setPageSize(page.getSize());
		apiPage.setTotal(page.getTotal());
		apiPage.setList(page.getRecords());
		return apiPage;
	}

}
