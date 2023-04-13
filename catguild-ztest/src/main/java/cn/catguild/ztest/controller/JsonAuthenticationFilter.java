package cn.catguild.ztest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * json 形式的认证过滤器
 * 继承 用户密码认证过滤率器，改变其中某些认证行为
 *
 * @author xiyan
 * @date 2023/4/13 14:38
 */
@Slf4j
public class JsonAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private boolean postOnly = true;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        if (this.postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        //if (!request.getContentType().contains(MediaType.APPLICATION_JSON_VALUE)){
        //    throw new AuthenticationServiceException("Authentication content-type not supported: " + request.getContentType());
        //}
        Map<String, String> loginData = new HashMap<>();
        try {
            loginData = new ObjectMapper().readValue(request.getInputStream(), Map.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String username = loginData.getOrDefault(getUsernameParameter(), "").trim();
        String password = loginData.getOrDefault(getPasswordParameter(), "").trim();

        log.info("所有入参【{}】，账号【{}】，密码【{}】", loginData, username, password);
        UsernamePasswordAuthenticationToken authRequest = UsernamePasswordAuthenticationToken.unauthenticated(username,
                password);
        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

}
