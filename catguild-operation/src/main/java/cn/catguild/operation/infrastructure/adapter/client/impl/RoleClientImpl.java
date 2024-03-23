package cn.catguild.operation.infrastructure.adapter.client.impl;

import cn.catguild.auth.api.client.AuthRoleClient;
import cn.catguild.common.api.ApiPage;
import cn.catguild.operation.infrastructure.adapter.client.RoleClient;
import cn.catguild.operation.infrastructure.adapter.client.dto.RoleClientDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author xiyan
 * @date 2024/1/17 17:54
 */
@Slf4j
@AllArgsConstructor
@Component
public class RoleClientImpl implements RoleClient {

	private final AuthRoleClient roleClient;

	@Override
	public ApiPage<RoleClientDTO> page() {
		return null;
	}

}
