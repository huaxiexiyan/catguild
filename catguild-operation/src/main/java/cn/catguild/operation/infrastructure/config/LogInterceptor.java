package cn.catguild.operation.infrastructure.config;

import cn.catguild.common.constant.LogConstant;
import cn.catguild.common.utility.TraceIdUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author xiyan
 * @date 2023/8/17 14:55
 */
@Component
public class LogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //如果有上层调用就用上层的ID
        String traceId = request.getHeader(LogConstant.TRACE_ID_HEADER);
        if (traceId == null) {
            traceId = TraceIdUtil.getTraceId();
        }
        MDC.put(LogConstant.TRACE_ID, traceId);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 清除 Trace ID，以避免内存泄漏
        MDC.remove(LogConstant.TRACE_ID);
    }

}