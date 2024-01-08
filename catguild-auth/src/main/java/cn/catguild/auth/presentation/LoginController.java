package cn.catguild.auth.presentation;

import cn.catguild.auth.application.TenantApplication;
import cn.catguild.auth.domain.Tenant;
import cn.catguild.common.utility.JSONUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2023/11/26 19:28
 */
@RequestMapping("/login")
@Controller
@AllArgsConstructor
@Slf4j
public class LoginController {

	private final TenantApplication tenantApplication;

	@GetMapping("")
	public String login() {
		return "login";
	}

	@PostMapping("/password")
	public String password(String redirectUri,
						   String username, String password,
						   String originalDomain,
						   HttpServletResponse response) {
		Tenant tenant = tenantApplication.getByDomainName(originalDomain);
		Long tenantId = tenant.getId();

		// 创建一个HttpClient实例
		HttpClient httpClient = HttpClient.newHttpClient();
		// 设置POST请求的URL
		String url = "http://127.0.0.1:20010/oauth2/token";
		URI uri = URI.create(url);
		// 构建POST请求的内容，这可以是一个JSON字符串、表单数据等
		Map<String, String> requestBodyMap = Map.of(
			"grant_type", "password",
			"tenantId", tenantId.toString(),
			"username", username,
			"password", password
		);
		// 将Map转换为application/x-www-form-urlencoded格式的字符串
		String requestBody = requestBodyMap.entrySet().stream()
			.map(entry -> encode(entry.getKey()) + "=" + encode(entry.getValue()))
			.collect(Collectors.joining("&"));
		String basicAuth = Base64.getEncoder().encodeToString(("catguild-admin-web:huaxiexiyan").getBytes(StandardCharsets.UTF_8));
		// 创建POST请求
		HttpRequest request = HttpRequest.newBuilder()
			.uri(uri)
			.header("Content-Type", "application/x-www-form-urlencoded")
			.header("Authorization", "Basic " + basicAuth)
			.POST(HttpRequest.BodyPublishers.ofString(requestBody))
			.build();
		try {
			// 发送请求并获取响应
			HttpResponse<String> responseToken = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
			// 获取响应状态码
			int statusCode = responseToken.statusCode();
			if (statusCode != 200) {
				throw new RuntimeException("登录失败！");
			}
			// 获取响应体
			String responseBody = responseToken.body();
			ObjectMapper instance = JSONUtils.getInstance();
			JsonNode jsonNode = instance.readTree(responseBody);
			JsonNode jsonNodeToken = jsonNode.get("access_token");

			String catToken = Base64.getEncoder().encodeToString(jsonNodeToken.textValue().getBytes(StandardCharsets.UTF_8));
			return "redirect:" + redirectUri + "?catToken=" + catToken;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	// 辅助方法：对字符串进行URL编码
	private String encode(String value) {
		return URLEncoder.encode(value, StandardCharsets.UTF_8);
	}

}
