package cn.catguild.auth.service.impl;

import cn.catguild.auth.service.Oauth2RegisteredClientService;
import cn.catguild.auth.service.repository.Oauth2RegisteredClientRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.stereotype.Service;

/**
 * 返回注册客户端资源 RegisteredClientRepository用于管理客户端的实例。
 *
 * @author xiyan
 * @date 2023/4/30 16:07
 */
@AllArgsConstructor
@Slf4j
@Service
public class Oauth2RegisteredClientServiceImpl implements Oauth2RegisteredClientService {

    private Oauth2RegisteredClientRepository oauth2RegisteredClientRepository;
    @Override
    public void save(RegisteredClient registeredClient) {
        oauth2RegisteredClientRepository.save(0L, registeredClient);
    }

    @Override
    public RegisteredClient findById(String id) {
        return oauth2RegisteredClientRepository.findById(0L, id);
    }

    @Override
    public RegisteredClient findByClientId(String clientId) {
        return oauth2RegisteredClientRepository.findByClientId(0L, clientId);
    }

}
