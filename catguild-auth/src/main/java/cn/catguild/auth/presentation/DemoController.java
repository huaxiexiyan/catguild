package cn.catguild.auth.presentation;

import cn.catguild.auth.application.TenantApplication;
import cn.catguild.auth.domain.Tenant;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author xiyan
 * @date 2023/11/26 19:28
 */
@RequestMapping("/login")
@Controller
@AllArgsConstructor
@Slf4j
public class DemoController {

    private final TenantApplication tenantApplication;

    // 辅助方法：对字符串进行URL编码
    private static String encode(String value) {
        try {
            return URLEncoder.encode(value, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Error encoding value: " + value, e);
        }
    }

    @GetMapping("")
    public String login() {
        return "login";
    }

    //http://127.0.0.1:20010/login?client_id=catguild-admin-web&redirect_uri=http://www.baidu.com&original_domain=localhost
    @PostMapping("/password")
    public String password(String clientId, String redirectUri, String originalDomain,
                           String username, String password) {
        List<Tenant> tenantByDomainName = tenantApplication.getTenantByDomainName(originalDomain);
        Long tenantId = tenantByDomainName.get(0).getId();

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

        String basicAuth = Base64.getEncoder().encodeToString((clientId+":huaxiexiyan").getBytes(StandardCharsets.UTF_8));

        //Y2F0Z3VpbGQtYWRtaW4td2ViOmh1YXhpZXhpeWFu
        // 创建POST请求
        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Authorization", "Basic "+basicAuth)
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        try {
            // 发送请求并获取响应
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // 获取响应状态码
            int statusCode = response.statusCode();
            System.out.println("Status Code: " + statusCode);

            // 获取响应头
            HttpHeaders headers = response.headers();
            headers.map().forEach((key, value) -> System.out.println(key + ": " + value));

            // 获取响应体
            String responseBody = response.body();
            System.out.println("Response Body: " + responseBody);
            String code = UUID.randomUUID().toString().replaceAll("-", "");
            return "redirect:" + redirectUri + "?code=" + code;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

}
