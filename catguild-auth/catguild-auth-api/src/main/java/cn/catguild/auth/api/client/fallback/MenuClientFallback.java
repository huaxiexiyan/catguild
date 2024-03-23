/**
 * Copyright (c) 2018-2028, Chill Zhuang 庄骞 (smallchill@163.com).
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.catguild.auth.api.client.fallback;

import cn.catguild.auth.api.client.MenuClient;
import cn.catguild.auth.api.dto.request.MenuPageRequest;
import cn.catguild.auth.api.dto.request.MenuTreeRequest;
import cn.catguild.auth.api.dto.response.MenuResponse;
import cn.catguild.auth.api.dto.response.MenuTreeResponse;
import cn.catguild.common.api.ApiPage;
import cn.catguild.common.api.ApiResponse;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Feign失败配置
 *
 * @author Chill
 */
@Component
public class MenuClientFallback implements MenuClient {


	@Override
	public ApiResponse<ApiPage<MenuResponse>> page(MenuPageRequest pageRequest) {
		return ApiResponse.fail("400","未获取到菜单信息");
	}

	@Override
	public ApiResponse<ApiPage<MenuResponse>> list() {
		return null;
	}

	@Override
	public ApiResponse<List<MenuTreeResponse>> tree(String appCode, MenuTreeRequest menuTreeRequest) {
		return null;
	}

}
