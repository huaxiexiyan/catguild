package cn.catguild.business.erp.infrastructure.adapter.external.client.impl;

import cn.catguild.business.erp.infrastructure.adapter.external.client.EmailClient;
import cn.catguild.system.api.EmailClientProto;
import cn.catguild.system.api.EmailClientServiceGrpc;
import cn.catguild.system.api.common.ApiCommonProto;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author xiyan
 * @date 2023/8/10 15:53
 */
@Slf4j
@Component
public class EmailClientImpl implements EmailClient {

    /**
     * 异步客户端
     */
    @GrpcClient("catguild-system")
    private EmailClientServiceGrpc.EmailClientServiceStub emailClientServiceAsyncStub;

    @Override
    public void sendHtmlTp(String address, String subject, Long tpId, Map<String, String> tpParam) {
        EmailClientProto.HtmlTpRequest htmlTpRequest = EmailClientProto.HtmlTpRequest.newBuilder()
                .setAddress(address)
                .setSubject(subject)
                .setTpId(tpId)
                .putAllTpParam(tpParam)
                .build();
        emailClientServiceAsyncStub.sendHtmlTp(htmlTpRequest, new StreamObserver<EmailClientProto.HtmlTpResponse>() {
            @Override
            public void onNext(EmailClientProto.HtmlTpResponse value) {
                ApiCommonProto.ApiResponse apiResponse = value.getApiResponse();
                if (apiResponse.getSuccess()) {
                    log.info("邮件服务调用成功！！");
                } else {
                    log.error("邮件服务调用异常！！响应参数=【{}】", apiResponse);
                }
            }

            @Override
            public void onError(Throwable t) {
                log.error("邮件服务调用异常！！", t);
            }

            @Override
            public void onCompleted() {
                log.info("邮件已发送！");
            }
        });
    }

}
