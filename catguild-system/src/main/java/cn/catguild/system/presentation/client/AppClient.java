package cn.catguild.system.presentation.client;

import cn.catguild.common.utility.CollectionUtils;
import cn.catguild.system.api.AppClientGrpc;
import cn.catguild.system.api.AppClientProto;
import cn.catguild.system.api.common.ApiCommonProto;
import cn.catguild.system.application.AppApplication;
import cn.catguild.system.domain.App;
import cn.catguild.system.presentation.converter.AppDTOConverter;
import cn.catguild.system.util.AuthUtil;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/8/1 17:36
 */
@Slf4j
@AllArgsConstructor
@GrpcService
public class AppClient extends AppClientGrpc.AppClientImplBase {

    private final AppApplication appApplication;

    private final AppDTOConverter appDTOConverter;

    @Override
    public void listApp(AppClientProto.AppRequest request, StreamObserver<AppClientProto.AppDTOListResponse> responseObserver) {
        // 提供返回值
        AppClientProto.AppDTOListResponse.Builder responseBuilder = AppClientProto.AppDTOListResponse.newBuilder();
        try {
            List<App> apps = appApplication.listAppByIds(AuthUtil.getTenantId(), request.getAppIdsList());
            List<AppClientProto.AppDTO> appList = apps.stream().map(app->{
                AppClientProto.AppDTO dto = appDTOConverter.toDTO(app);
                if (CollectionUtils.isNotEmpty(app.getMenus())){
                    dto = dto.toBuilder()
                            .addAllMenus(appDTOConverter.toMenuDTO(app, app.getMenus()))
                            .build();
                }
                return dto;
            }).toList();
            // 1、构建响应消息内容
            responseBuilder.setApiResponse(ApiCommonProto.ApiResponse.newBuilder()
                            .setSuccess(true)
                            .build())
                    .addAllApps(appList)
                    .build();
        } catch (Exception e) {
            responseBuilder.setApiResponse(ApiCommonProto.ApiResponse.newBuilder()
                            .setSuccess(false)
                            .build());
            log.error("服务调用失败", e);
        }
        // 2、放入响应流中
        responseObserver.onNext(responseBuilder.build());
        responseObserver.onCompleted();
    }

}
