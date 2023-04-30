package cn.catguild.auth.service.repository;

import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;

/**
 * @author xiyan
 * @date 2023/4/30 15:53
 */
public interface Oauth2RegisteredClientRepository {

    RegisteredClient findByClientId(long l, String clientId);

    RegisteredClient findById(long l, String id);

    void save(long l, RegisteredClient registeredClient);

}
