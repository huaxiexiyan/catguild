package cn.catguild.operation.infrastructure.adapter.client;

import cn.catguild.common.api.ApiPage;
import cn.catguild.operation.infrastructure.adapter.client.dto.RoleClientDTO;

/**
 * @author xiyan
 * @date 2024/1/17 17:44
 */
public interface RoleClient {
	ApiPage<RoleClientDTO> page();

}
