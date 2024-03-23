package cn.catguild.operation.infrastructure.config;

import cn.catguild.common.utility.transplant.StringPool;
import cn.catguild.operation.util.AuthUtil;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.client.RestTemplate;

/**
 * @author xiyan
 * @date 2024/1/8 17:38
 */
@Slf4j
@Configuration
public class OpenFeignConfig {

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> {
			// 被请求方设置token，实现token中转
			requestTemplate.header("Authorization",
				OAuth2AccessToken.TokenType.BEARER.getValue() +
					StringPool.SPACE +
					AuthUtil.getTokenValue());
		};
	}


}
