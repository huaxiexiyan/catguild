package cn.catguild.auth.application;

import cn.catguild.auth.domain.NetworkAppAuthConfig;
import cn.catguild.auth.infrastructure.NetworkAppAuthConfigRepository;
import cn.catguild.auth.infrastructure.adapter.cache.CacheClient;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/9/26 11:21
 */
@Slf4j
@Transactional(rollbackFor = Exception.class)
@AllArgsConstructor
@Component
public class NetworkAppAuthApplication {

    private NetworkAppAuthConfigRepository networkAppAuthConfigRepository;

    private CacheClient cacheClient;

    /**
     * 获取应用列表
     *
     * @return list
     */
    public List<NetworkAppAuthConfig> listNetworkAppAuth() {
        List<NetworkAppAuthConfig> list = networkAppAuthConfigRepository.findAll();
        cacheClient.save("auth:network_app_auth_config:list:all", list);
        return list;
    }


    public void updateAppAuthConfig(Long id, NetworkAppAuthConfig appAuthConfig) {
        networkAppAuthConfigRepository.findById(id).ifPresent(item->{
            if (StringUtils.hasText(appAuthConfig.getAccessToken())){
                item.setAccessToken(appAuthConfig.getAccessToken());
            }
            if (StringUtils.hasText(appAuthConfig.getRefreshToken())){
                item.setRefreshToken(appAuthConfig.getRefreshToken());
            }
            if (appAuthConfig.getExpiresIn() != null){
                item.setExpiresIn(appAuthConfig.getExpiresIn());
            }
            networkAppAuthConfigRepository.saveAndFlush(item);
        });
    }

}
