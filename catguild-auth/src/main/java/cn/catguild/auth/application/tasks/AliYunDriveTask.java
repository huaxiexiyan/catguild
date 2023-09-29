package cn.catguild.auth.application.tasks;

import cn.catguild.auth.application.NetworkAppAuthApplication;
import cn.catguild.auth.domain.NetworkAppAuthConfig;
import cn.catguild.auth.infrastructure.adapter.cache.CacheClient;
import cn.catguild.common.constant.LogConstant;
import cn.catguild.common.utility.TraceIdUtil;
import lombok.RequiredArgsConstructor;
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
import java.util.List;

/**
 * 阿里云盘定时任务
 *
 * @author xiyan
 * @date 2023/9/18 10:15
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AliYunDriveTask {

    private final NetworkAppAuthApplication networkAppAuthApplication;
    private final CacheClient cacheClient;
    @Value("${aliyundrive.url}")
    @Setter
    private String url;

    @Scheduled(cron="0 0 8 * * ?")
    public void dailyCheckIn() throws IOException, InterruptedException {
        MDC.put(LogConstant.TRACE_ID, TraceIdUtil.getTraceId());
        log.info("执行定时任务 {}", url);
        networkAppAuthApplication.listNetworkAppAuth();
        HttpClient httpClient = HttpClient.newBuilder().build();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .build();
        HttpResponse<String> send = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        // 执行
        log.info("执行定时任务 {}", send);
    }

    //@Scheduled(cron="0 0 10 * * ?")
    @Scheduled(cron="0 0/2 * * * ?")
    public void refreshAuth(){
        MDC.put(LogConstant.TRACE_ID, TraceIdUtil.getTraceId());
        log.info("更新授权参数开始");
        String prefix = "auth:network_app_auth_config:new_auth:";
        List<String> keys = cacheClient.getKeys(prefix + "*");
        keys.forEach(key->{
            NetworkAppAuthConfig networkAppAuthConfig = cacheClient.getObject(key,NetworkAppAuthConfig.class);
            Long id = Long.parseLong(key.replace(prefix, ""));
            NetworkAppAuthConfig networkAppAuthConfigUpdate = new NetworkAppAuthConfig();
            networkAppAuthConfigUpdate.setAccessToken(networkAppAuthConfig.getAccessToken());
            networkAppAuthConfigUpdate.setRefreshToken(networkAppAuthConfig.getRefreshToken());
            networkAppAuthConfigUpdate.setExpiresIn(networkAppAuthConfig.getExpiresIn());
            networkAppAuthApplication.updateAppAuthConfig(id, networkAppAuthConfigUpdate);
            cacheClient.remove(key);
        });
        log.info("更新授权参数结束");
    }

}
