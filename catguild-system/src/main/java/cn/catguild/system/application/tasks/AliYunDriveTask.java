package cn.catguild.system.application.tasks;

import cn.catguild.common.constant.LogConstant;
import cn.catguild.common.utility.TraceIdUtil;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * 阿里云盘定时任务
 *
 * @author xiyan
 * @date 2023/9/18 10:15
 */
@Slf4j
@Component
public class AliYunDriveTask {

    @Value("${aliyundrive.url}")
    @Setter
    private String url;

    //@Scheduled(cron="0 0 8 * * ?")
    @Scheduled(cron = "0 0/1 * * * ?")
    public void dailyCheckIn() throws IOException, InterruptedException {
        MDC.put(LogConstant.TRACE_ID, TraceIdUtil.getTraceId());
        HttpClient httpClient = HttpClient.newBuilder().build();
        log.info("执行定时任务 {}", url);
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        // 执行
        log.info("执行定时任务 {}", send);
    }

}
