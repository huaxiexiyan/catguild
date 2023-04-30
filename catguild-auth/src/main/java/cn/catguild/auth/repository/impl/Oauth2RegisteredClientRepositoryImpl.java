package cn.catguild.auth.repository.impl;

import cn.catguild.auth.repository.domain.Oauth2RegisteredClientDO;
import cn.catguild.auth.repository.mapper.Oauth2RegisteredClientMapper;
import cn.catguild.auth.service.repository.Oauth2RegisteredClientRepository;
import cn.catguild.auth.util.AuthCommonUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.stereotype.Repository;

import java.util.Arrays;

/**
 * @author xiyan
 * @date 2023/4/30 15:53
 */
@AllArgsConstructor
@Slf4j
@Repository
public class Oauth2RegisteredClientRepositoryImpl implements Oauth2RegisteredClientRepository {

    private Oauth2RegisteredClientMapper oauth2RegisteredClientMapper;

    private static RegisteredClient toRegisteredClient(Oauth2RegisteredClientDO clientDO) {
        return RegisteredClient.withId(clientDO.getId())
                .clientId(clientDO.getClientId())
                //.clientIdIssuedAt(clientDO.getClientIdIssuedAt())
                .clientSecret(clientDO.getClientSecret())
                //.clientSecretExpiresAt(clientDO.getClientSecretExpiresAt())
                .clientName(clientDO.getClientName())
                .clientAuthenticationMethods((clientAuthenticationMethod) -> {
                    String[] split = clientDO.getClientAuthenticationMethods().split(",");
                    for (String s : split) {
                        ClientAuthenticationMethod authenticationMethod = AuthCommonUtil.getClientAuthenticationMethod(s);
                        if (authenticationMethod != null) {
                            clientAuthenticationMethod.add(authenticationMethod);
                        }
                    }
                })
                .authorizationGrantTypes((authorizationGrantType) -> {
                    String[] split = clientDO.getAuthorizationGrantTypes().split(",");
                    for (String s : split) {
                        AuthorizationGrantType grantType = AuthCommonUtil.getAuthorizationGrantType(s);
                        if (grantType != null) {
                            authorizationGrantType.add(grantType);
                        }
                    }
                })
                .redirectUris((redirectUri) -> {
                    String[] split = clientDO.getRedirectUris().split(",");
                    redirectUri.addAll(Arrays.asList(split));
                })
                .scopes((scope) -> {
                    String[] split = clientDO.getScopes().split(",");
                    scope.addAll(Arrays.asList(split));
                })
                .clientSettings(ClientSettings.builder().requireAuthorizationConsent(true).build())
                //.tokenSettings(TokenSettings.builder().build())
                .build();
    }

    @Override
    public RegisteredClient findByClientId(long guildId, String clientId) {
        Oauth2RegisteredClientDO clientDO = oauth2RegisteredClientMapper.selectOne(Wrappers.<Oauth2RegisteredClientDO>lambdaQuery()
                .eq(Oauth2RegisteredClientDO::getGuildId, guildId)
                .eq(Oauth2RegisteredClientDO::getClientId, clientId));
        RegisteredClient registeredClient = toRegisteredClient(clientDO);
        return registeredClient;
    }

    @Override
    public RegisteredClient findById(long guildId, String id) {
        Oauth2RegisteredClientDO clientDO = oauth2RegisteredClientMapper.selectOne(Wrappers.<Oauth2RegisteredClientDO>lambdaQuery()
                .eq(Oauth2RegisteredClientDO::getGuildId, guildId)
                .eq(Oauth2RegisteredClientDO::getId, id));
        RegisteredClient registeredClient = toRegisteredClient(clientDO);
        return registeredClient;
    }

    @Override
    public void save(long l, RegisteredClient registeredClient) {

    }

}
