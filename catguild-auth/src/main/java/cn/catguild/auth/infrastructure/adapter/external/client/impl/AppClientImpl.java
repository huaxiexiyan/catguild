package cn.catguild.auth.infrastructure.adapter.external.client.impl;

import cn.catguild.auth.infrastructure.adapter.external.client.AppClient;
import cn.catguild.auth.infrastructure.adapter.external.client.converter.AppDTOConverter;
import cn.catguild.system.api.AppClientGrpc;
import cn.catguild.system.api.AppClientProto;
import cn.catguild.system.api.dto.AppDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/31 15:21
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class AppClientImpl implements AppClient {

    private final AppDTOConverter appDTOConverter;
    @GrpcClient("catguild-system")
    private AppClientGrpc.AppClientBlockingStub appClientBlockingStub;

    @Override
    public List<AppDTO> listApp(List<Long> appIds) {
        AppClientProto.AppRequest request = AppClientProto.AppRequest.newBuilder()
                .addAllAppIds(appIds)
                .build();
        AppClientProto.AppDTOListResponse response = appClientBlockingStub.listApp(request);
        if (response.hasApiResponse() && response.getApiResponse().getSuccess()) {
            List<AppClientProto.AppDTO> appsList = response.getAppsList();
            return appDTOConverter.fromDTO(appsList);
        } else {
            log.error("system服务调用失败:{}", response.getApiResponse());
        }
        return null;
    }

}
