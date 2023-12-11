package cn.catguild.auth.presentation.client;

import cn.catguild.auth.api.PermissionsClientProto;
import cn.catguild.auth.api.PermissionsClientServiceGrpc;
import cn.catguild.auth.api.common.ApiCommonProto;
import cn.catguild.auth.application.PermissionsApplication;
import cn.catguild.auth.domain.Resource;
import cn.catguild.auth.oauth.util.AuthUtil;
import cn.catguild.auth.presentation.client.converter.AuthResourceConverter;
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

    private final AuthResourceConverter authResourceConverter;


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
                        .setResourceType(resource.getRefType())
                        .build()).toList())
                .setApiResponse(ApiCommonProto.ApiResponse.newBuilder()
                        .setSuccess(true)
                        .build())
                .build();
        // 2、放入响应流中
        responseObserver.onNext(authResourceResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void syncResource(PermissionsClientProto.SyncResourceRequest request, StreamObserver<PermissionsClientProto.SyncResourceResponse> responseObserver) {
        // 提供返回值
        boolean resultFlag = true;
        try {
            permissionsApplication.syncResource(AuthUtil.getTenantId(), request.getAppId(), request.getResourceType(), request.getResourceIdsList().stream().toList());
        }catch (Exception e){
            resultFlag = false;
        }
        // 1、构建响应消息内容
        PermissionsClientProto.SyncResourceResponse syncResourceResponse = PermissionsClientProto.SyncResourceResponse.newBuilder()
                .setApiResponse(ApiCommonProto.ApiResponse.newBuilder()
                        .setSuccess(resultFlag)
                        .build())
                .build();
        // 2、放入响应流中
        responseObserver.onNext(syncResourceResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void clearResource(PermissionsClientProto.ClearResourceRequest request, StreamObserver<PermissionsClientProto.ClearResourceResponse> responseObserver) {
        // 提供返回值
        boolean resultFlag = true;
        try {
            permissionsApplication.clearResource(AuthUtil.getTenantId(), request.getAppId(), request.getResourceType());
        } catch (Exception e) {
            resultFlag = false;
        }
        // 1、构建响应消息内容
        PermissionsClientProto.ClearResourceResponse clearResourceResponse = PermissionsClientProto.ClearResourceResponse.newBuilder()
                .setApiResponse(ApiCommonProto.ApiResponse.newBuilder()
                        .setSuccess(resultFlag)
                        .build())
                .build();
        responseObserver.onNext(clearResourceResponse);
        responseObserver.onCompleted();
    }

}
