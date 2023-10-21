package cn.catguild.auth.presentation.client;

import cn.catguild.auth.api.PermissionsClientProto;
import cn.catguild.auth.api.PermissionsClientServiceGrpc;
import cn.catguild.auth.application.PermissionsApplication;
import cn.catguild.auth.domain.Resource;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/1 17:36
 */
@AllArgsConstructor
@GrpcService
public class PermissionsClient extends PermissionsClientServiceGrpc.PermissionsClientServiceImplBase {

    private final PermissionsApplication permissionsApplication;


    @Override
    public void getAuthResourceByType(PermissionsClientProto.AuthResourceRequest request,
                                      StreamObserver<PermissionsClientProto.AuthResourceResponse> responseObserver) {
        // 提供返回值
        List<Resource> resources = permissionsApplication.getAuthResourceByType(request.getAppId(), request.getResourceType());

        // 1、构建响应消息内容
        PermissionsClientProto.AuthResourceResponse authResourceResponse = PermissionsClientProto.AuthResourceResponse.newBuilder()
                .addAllAuthResources(resources.stream().map(resource -> PermissionsClientProto.AuthResource.newBuilder()
                        .setAuthId(resource.getId())
                        .setResourceId(resource.getRefId())
                        .setResourceType(resource.getName())
                        .build()).toList())
                .build();
        // 2、放入响应流中
        responseObserver.onNext(authResourceResponse);
        responseObserver.onCompleted();
    }


}
