package cn.catguild.system.presentation.client;

import cn.catguild.system.api.IdGenerationClientGrpc;
import cn.catguild.system.api.IdGenerationClientProto;
import cn.catguild.system.api.common.ApiCommonProto;
import cn.catguild.system.infrastructure.idgeneration.IdGenerationService;
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


    private IdGenerationService idGenerationService;

    /**
     * 实现服务
     *
     * @param request          请求消息
     * @param responseObserver 响应消息
     */
    @Override
    public void nextId(Empty request, StreamObserver<IdGenerationClientProto.IdResponse> responseObserver) {
        Long nextId = idGenerationService.nextId();
        // 提供返回值
        // 1、构建响应消息内容
        IdGenerationClientProto.IdResponse.Builder builder = IdGenerationClientProto.IdResponse.newBuilder();
        builder.setId(nextId);
        IdGenerationClientProto.IdResponse build = builder.build();
        // 2、放入响应流中
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }

    @Override
    public void nextUid(Empty request, StreamObserver<IdGenerationClientProto.UIdResponse> responseObserver) {
        try {
            Integer uid = idGenerationService.nextUID();
            responseObserver.onNext(IdGenerationClientProto.UIdResponse.newBuilder()
                    .setApiResponse(ApiCommonProto.ApiResponse.newBuilder()
                            .setSuccess(true)
                            .build())
                    .setUid(uid)
                    .build());
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onNext(IdGenerationClientProto.UIdResponse.newBuilder()
                    .setApiResponse(ApiCommonProto.ApiResponse.newBuilder()
                            .setSuccess(false)
                            .setErrorCode("500")
                            .setErrorMessage(e.getMessage())
                            .build())
                    .build());
            responseObserver.onCompleted();
        }
    }

}
