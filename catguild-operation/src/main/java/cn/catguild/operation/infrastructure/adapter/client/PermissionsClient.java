package cn.catguild.operation.infrastructure.adapter.client;

import cn.catguild.operation.infrastructure.adapter.client.dto.PermissionDTO;

import java.util.List;

/**
 * @author xiyan
 * @date 2024/1/8 16:10
 */
public interface PermissionsClient {
	List<PermissionDTO> getByRefType(String refType);

}
