package cn.catguild.business.erp.infrastructure.adapter.external.client.impl;

import cn.catguild.business.erp.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.system.api.IdGenerationClientGrpc;
import cn.catguild.system.api.IdGenerationClientProto;
import cn.catguild.system.api.common.ApiCommonProto;
import com.google.protobuf.Empty;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

/**
 * @author xiyan
 * @date 2023/8/3 16:05
 */
@Slf4j
@Component
public class IdGenerationClientImpl implements IdGenerationClient {

    @GrpcClient("catguild-system")
    private IdGenerationClientGrpc.IdGenerationClientBlockingStub idGenerationClientBlockingStub;

    @Override
    public Long nextId() {
        IdGenerationClientProto.IdResponse idResponse = idGenerationClientBlockingStub.nextId(Empty.newBuilder()
                .build());
        long id = idResponse.getId();
        log.info("远程调用成功，获取id=【{}】", id);
        return id;
    }

    @Override
    public Integer nextUid() {
        IdGenerationClientProto.UIdResponse uIdResponse = idGenerationClientBlockingStub.nextUid(Empty.newBuilder()
                .build());
        ApiCommonProto.ApiResponse apiResponse = uIdResponse.getApiResponse();
        if (apiResponse.getSuccess()) {
            return uIdResponse.getUid();
        } else {
            log.error("uid获取失败:{}", apiResponse);
        }
        return null;
    }


}
