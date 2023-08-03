package cn.catguild.system.presentation.client;

import cn.catguild.common.api.ApiResponse;
import cn.catguild.system.api.IdGenerationClientGrpc;
import cn.catguild.system.api.IdGenerationClientProto;
import cn.catguild.system.presentation.IdGenerationResource;
import com.google.protobuf.Empty;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author xiyan
 * @date 2023/8/1 17:36
 */
@AllArgsConstructor
@GrpcService
public class IdGenerationClient extends IdGenerationClientGrpc.IdGenerationClientImplBase {


    private IdGenerationResource idGenerationResource;

    /**
     * 实现服务
     *
     * @param request 请求消息
     * @param responseObserver 响应消息
     */
    @Override
    public void nextId(Empty request, StreamObserver<IdGenerationClientProto.IdResponse> responseObserver) {
        ApiResponse<Long> stringApiResponse = idGenerationResource.nextId();
        // 提供返回值
        // 1、构建响应消息内容
        IdGenerationClientProto.IdResponse.Builder builder = IdGenerationClientProto.IdResponse.newBuilder();
        builder.setId(stringApiResponse.getData());
        IdGenerationClientProto.IdResponse build = builder.build();
        // 2、放入响应流中
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

}
