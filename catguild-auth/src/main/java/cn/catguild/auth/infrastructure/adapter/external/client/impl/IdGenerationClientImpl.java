package cn.catguild.auth.infrastructure.adapter.external.client.impl;

import cn.catguild.auth.infrastructure.adapter.external.client.IdGenerationClient;
import cn.catguild.system.api.IdGenerationClientGrpc;
import cn.catguild.system.api.IdGenerationClientProto;
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

    @GrpcClient("cloud-grpc-server")
    private IdGenerationClientGrpc.IdGenerationClientBlockingStub idGenerationClientBlockingStub;

    @Override
    public Long nextId() {
        IdGenerationClientProto.IdResponse idResponse = idGenerationClientBlockingStub.nextId(Empty.newBuilder().build());
        long id = idResponse.getId();
        log.info("远程调用成功，获取id=【{}】", id);
        return id;
    }




}
