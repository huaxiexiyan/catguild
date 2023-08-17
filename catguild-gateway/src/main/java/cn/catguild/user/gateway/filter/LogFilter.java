package cn.catguild.user.gateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * @author xiyan
 * @date 2023/8/17 14:22
 */
@Slf4j
@Component
public class LogFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 仅仅传递这个值，本身的异步性，正确管理gateway自己MDC比较麻烦，此处暂不处理
        exchange.getRequest().mutate().headers(headers -> headers.add("X-Trace-ID", generateTraceId()));
        return chain.filter(exchange);
    }

    private String generateTraceId() {
        // Implement your Trace ID generation logic here
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

}
