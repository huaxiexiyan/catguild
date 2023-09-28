package cn.catguild.auth.infrastructure.adapter.cache.impl;

import cn.catguild.auth.domain.NetworkAppAuthConfig;
import cn.catguild.auth.infrastructure.NetworkAppAuthConfigRepository;
import cn.catguild.auth.infrastructure.adapter.cache.CacheClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class RedisCacheClientTest {

    @Autowired
    private NetworkAppAuthConfigRepository networkAppAuthConfigRepository;

    @Autowired
    private CacheClient cacheClient;

    @Test
    void getString() {
        log.info("更新授权参数开始");
        //
        //List<NetworkAppAuthConfig> all = networkAppAuthConfigRepository.findAll();
        //cacheClient.save("test:aa",all);
        List<String> keys = cacheClient.getKeys("auth:network_app_auth_config:new_auth:" + "*");
        keys.forEach(key -> {
            String jsonStr = cacheClient.getString(key);
            NetworkAppAuthConfig object = cacheClient.getObject(key, NetworkAppAuthConfig.class);

            log.info("{}", object);
            //ObjectMapper objectMapper = new ObjectMapper();
            //try {
            //    JsonNode jsonNode = objectMapper.readTree(jsonStr);
            //    log.info("{}",jsonNode);
            //} catch (JsonProcessingException e) {
            //    throw new RuntimeException(e);
            //}
            //NetworkAppAuthConfig networkAppAuthConfig = JSONUtils.parse(jsonStr, NetworkAppAuthConfig.class);
            //log.info("{}",networkAppAuthConfig);
            //log.info("{}",JSONUtils.toJsonStr(networkAppAuthConfig));
        });
        log.info("更新授权参数结束");
    }
}