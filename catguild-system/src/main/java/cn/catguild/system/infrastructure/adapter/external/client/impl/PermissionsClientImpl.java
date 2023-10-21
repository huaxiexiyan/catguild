package cn.catguild.system.infrastructure.adapter.external.client.impl;

import cn.catguild.auth.api.PermissionsClientProto;
import cn.catguild.auth.api.PermissionsClientServiceGrpc;
import cn.catguild.auth.api.common.ApiCommonProto;
import cn.catguild.system.infrastructure.adapter.external.client.PermissionsClient;
import cn.catguild.system.infrastructure.adapter.external.client.converter.AuthResourceDTOConverter;
import cn.catguild.system.infrastructure.adapter.external.client.dto.AuthResourceDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/10 15:53
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class PermissionsClientImpl implements PermissionsClient {

    private final AuthResourceDTOConverter authResourceConverter;

    /**
     * 异步客户端
     */
    @GrpcClient("catguild-auth")
    private PermissionsClientServiceGrpc.PermissionsClientServiceBlockingStub permissionsClientServiceBlockingStub;


    @Override
    public List<AuthResourceDTO> getAuthResourceByType(Long appId, String resourceType) {
        PermissionsClientProto.AuthResourceRequest authResourceRequest = PermissionsClientProto.AuthResourceRequest.newBuilder()
                .setAppId(appId)
                .setResourceType(resourceType)
                .build();
        PermissionsClientProto.AuthResourceResponse authResourceResponse = permissionsClientServiceBlockingStub.getAuthResourceByType(authResourceRequest);
        ApiCommonProto.ApiResponse apiResponse = authResourceResponse.getApiResponse();
        if (apiResponse.getSuccess()) {
            List<PermissionsClientProto.AuthResource> authResourcesList = authResourceResponse.getAuthResourcesList();
            if (CollectionUtils.isEmpty(authResourcesList)){
                return new ArrayList<>();
            }
            return authResourcesList.stream()
                    .map(authResourceConverter::fromDTO)
                    .toList();
        } else {
            log.error("uid获取失败:{}", apiResponse);
        }
        throw new RuntimeException("授权服务调用异常");
    }


}
