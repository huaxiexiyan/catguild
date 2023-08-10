package cn.catguild.auth.infrastructure.adapter.external.client;

import java.util.Map;

/**
 * @author xiyan
 * @date 2023/8/10 15:53
 */
public interface EmailClient {

    void sendHtmlTp(String address, String subject, Long tpId, Map<String, String> tpParam);

}
