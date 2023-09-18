package cn.catguild.common.utility;

import java.util.UUID;

/**
 * @author xiyan
 * @date 2023/9/18 10:21
 */
public class TraceIdUtil {

    public static String getTraceId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
