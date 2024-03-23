package cn.catguild.operation.infrastructure.adapter.client.impl;

import cn.catguild.auth.api.client.AuthPermissionsClient;
import cn.catguild.auth.api.dto.AuthPermissionDTO;
import cn.catguild.operation.infrastructure.adapter.client.PermissionsClient;
import cn.catguild.operation.infrastructure.adapter.client.dto.PermissionDTO;
import cn.catguild.operation.infrastructure.constant.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiyan
 * @date 2024/1/8 16:19
 */
@Slf4j
@AllArgsConstructor
@Component
public class PermissionsClientImpl implements PermissionsClient {

	private final AuthPermissionsClient permissionsClient;

	@Override
	public List<PermissionDTO> getByRefType(String refType) {
		List<AuthPermissionDTO> byRefType = permissionsClient.getByRefType(CommonConstant.APP_CODE, refType);
		return byRefType.stream().map(dto -> {
			PermissionDTO permissionDTO = new PermissionDTO();
			BeanUtils.copyProperties(dto, permissionDTO);
			return permissionDTO;
		}).toList();
	}

}
