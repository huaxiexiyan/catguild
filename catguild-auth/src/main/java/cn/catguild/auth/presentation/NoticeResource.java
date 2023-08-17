package cn.catguild.auth.presentation;

import cn.catguild.auth.util.SseEmitterUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xiyan
 * @date 2023/8/16 16:43
 */
@AllArgsConstructor
@Slf4j
@RequestMapping("/notices")
@RestController
public class NoticeResource {

    private static AtomicInteger count = new AtomicInteger(0);

    /**
     * 用于创建连接
     */
    @GetMapping("/connect/{userId}")
    public SseEmitter connect(@PathVariable String userId) {
        return SseEmitterUtil.connect(userId);
    }

    @Scheduled(fixedDelay = 5,timeUnit = TimeUnit.MINUTES)
    public void test(){
        SseEmitterUtil.batchSendMessage(String.valueOf(count.getAndIncrement()));
    }

}
