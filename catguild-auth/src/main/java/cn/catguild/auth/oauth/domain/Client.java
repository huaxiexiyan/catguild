package cn.catguild.auth.oauth.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.Comment;

import java.time.Instant;

/**
 * 授权客户端
 *
 * @author xiyan
 * @date 2023/6/6 15:23
 */
@Data
@Entity
@Table(name = "`oauth2_client`")
public class Client{

    @Id
    private String id;
    @Comment("客户端唯一标识id")
    private String clientId;
    @Comment("客户端唯一标识过期时间")
    private Instant clientIdIssuedAt;
    @Comment("客户端唯一标识密钥")
    private String clientSecret;
    @Comment("客户端唯一标识密钥过期时间")
    private Instant clientSecretExpiresAt;
    @Comment("客户端名称")
    private String clientName;
    @Column(length = 1000)
    private String clientAuthenticationMethods;
    @Column(length = 1000)
    private String authorizationGrantTypes;
    @Column(length = 1000)
    @Comment("回调地址")
    private String redirectUris;
    @Column(length = 1000)
    private String postLogoutRedirectUris;
    @Column(length = 1000)
    @Comment("授权范围")
    private String scopes;
    @Column(length = 2000)
    private String clientSettings;
    @Column(length = 2000)
    private String tokenSettings;

}
