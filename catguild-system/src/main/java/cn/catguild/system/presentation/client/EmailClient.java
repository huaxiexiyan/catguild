package cn.catguild.system.presentation.client;

import cn.catguild.system.api.EmailClientProto;
import cn.catguild.system.api.EmailClientServiceGrpc;
import cn.catguild.system.api.common.ApiCommonProto;
import cn.catguild.system.infrastructure.message.EmailService;
import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author xiyan
 * @date 2023/8/10 15:42
 */
@AllArgsConstructor
@GrpcService
public class EmailClient extends EmailClientServiceGrpc.EmailClientServiceImplBase {

    private final EmailService emailService;

    @Override
    public void sendHtmlTp(EmailClientProto.HtmlTpRequest request, StreamObserver<EmailClientProto.HtmlTpResponse> responseObserver) {
        // 获取参数
        emailService.sendHtmlTpMail(request.getAddress(), request.getSubject(), request.getTpId(), request.getTpParamMap());
        // 响应参数
        responseObserver.onNext(EmailClientProto.HtmlTpResponse.newBuilder()
                .setApiResponse(ApiCommonProto.ApiResponse.newBuilder()
                        .setSuccess(true)
                        .build())
                .build());
        responseObserver.onCompleted();
    }

}
