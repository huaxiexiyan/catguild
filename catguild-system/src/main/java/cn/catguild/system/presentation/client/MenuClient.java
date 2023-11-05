package cn.catguild.system.presentation.client;

import cn.catguild.system.api.MenuClientGrpc;
import cn.catguild.system.api.MenuClientProto;
import cn.catguild.system.api.common.ApiCommonProto;
import cn.catguild.system.application.AppApplication;
import cn.catguild.system.application.MenuApplication;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author xiyan
 * @date 2023/8/1 17:36
 */
@AllArgsConstructor
@GrpcService
public class MenuClient extends MenuClientGrpc.MenuClientImplBase {

    private final MenuApplication menuApplication;

    private final AppApplication application;

    @Override
    public void listMenuByAppIds(MenuClientProto.listMenuByAppIdsRequest request, StreamObserver<MenuClientProto.MenuDTOResponse> responseObserver) {
        // 提供返回值
        MenuClientProto.MenuDTOResponse response;
        try {
            //List<Menu> menus = menuApplication.listMenuByAppIds(AuthUtil.getTenantId(), request.getAppIdsList());
            // 1、构建响应消息内容
            response = MenuClientProto.MenuDTOResponse.newBuilder()
                    .setApiResponse(ApiCommonProto.ApiResponse.newBuilder()
                            .setSuccess(true)
                            .build())
                    .build();
            responseObserver.onNext(response);
        } catch (Exception e) {
            response = MenuClientProto.MenuDTOResponse.newBuilder()
                    .setApiResponse(ApiCommonProto.ApiResponse.newBuilder()
                            .setSuccess(false)
                            .build())
                    .build();
        }
        // 2、放入响应流中
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
