package cn.catguild.system.infrastructure.adapter.external.client;

import cn.catguild.system.infrastructure.adapter.external.client.dto.AuthResourceDTO;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/10 15:53
 */
public interface PermissionsClient {
    List<AuthResourceDTO> getAuthResourceByType(Long appId, String resourceType);

    void syncResource(Long appId, String resourceType, List<Long> resourceIds);


    void clearResource(Long appId, String resourceType);

}
