package cn.catguild.ztest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Security 安全配置
 * 1、自定义登录请求地址
 * 2、自定义登录成功、失败响应
 * 3、请求、响应以json形式进行
 * 4、使用jwt令牌
 * 5、令牌存储到缓存中
 * 6、登出移除缓存
 * 7、鉴权先查令牌是否存在
 *
 * @author xiyan
 * @date 2023/4/13 11:21
 */
@Configuration
public class SecurityConfig {


    /**
     * SecurityFilterChain 安全过滤链对象，
     * 一个spring bean，没有
     * {@link DelegatingFilterProxy}
     * 是 Spring 提供的一个Filter实现，它允许允许Servlet容器的生命周期和Spring的ApplicationContext之间进行桥接。
     * 虽然Servlet容器允许使用自己的标准注册Filter实例，但它不知道Spring定义的Beans。
     * 您可以通过标准Servlet容器机制注册DelegatingFilterProxy，但将所有工作委托给实现Filter的Spring Bean。
     * <p>
     * {@link FilterChainProxy} FilterChainProxy是  Spring Security使用的核心
     * SpringSecurity的Servlet支持包含在FilterChainProxy中。FilterChainProxy是Spring Security提供的一种特殊筛选器，
     * 允许通过SecurityFilterChain将其委托给许多筛选器实例。
     * 由于FilterChainProxy是一个Bean，它通常封装在DelegatingFilterProxy中。
     * <p>
     * SecurityFilterChain中的安全过滤器通常是Bean，但它们是用FilterChainProxy而不是DelegatingFilterProxy注册的。
     * 与直接向Servlet容器或DelegatingFilterProxy注册相比，FilterChainProxy有很多优势。
     * 首先，它为Spring Security的所有Servlet支持提供了一个起点。
     * 由于这个原因，如果你试图对Spring Security的Servlet支持进行故障诊断，在FilterChainProxy中添加一个调试点是一个很好的开始
     * <p>
     * {@link SecurityFilterChain}
     * FilterChainProxy使用SecurityFilterChain来确定应为当前请求调用哪些Spring Security Filter实例。
     *
     * @param http
     * @return
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        // @formatter:off
        // * 1、自定义登录请求地址
        // * 2、自定义登录成功、失败响应
        // * 3、请求、响应以json形式进行
        // * 4、使用jwt令牌
        // * 5、令牌存储到缓存中
        // * 6、登出移除缓存
        // * 7、鉴权先查令牌是否存在
        // 如果配置这个，那么默认的行为都会被取消
        http.csrf().disable()// token 禁用csrf
                //.formLogin()// 首先要配置登录接口行为,不配就没有 其中会 new UsernamePasswordAuthenticationFilter 这个过滤器，并添加到过滤链中
                //.loginProcessingUrl("/user/auth/login")// 登录处理接口路径，这个路径只支持post（因为生成的RequestMatcher写死了POST方法）
                //.and()
                .addFilterAt(getLoginFilter(),UsernamePasswordAuthenticationFilter.class)
                //没有认证时，在这里处理结果，不要重定向
                .exceptionHandling()
                .authenticationEntryPoint((req, resp, authException) -> {
                            resp.setContentType("application/json;charset=utf-8");
                            resp.setStatus(401);
                            PrintWriter out = resp.getWriter();
                            Map<String,String> respMsg = new HashMap<>();
                            respMsg.put("msg", "访问失败!");
                            out.write(new ObjectMapper().writeValueAsString(respMsg));
                            out.flush();
                            out.close();
                        }
                );
        // @formatter:on
        return http.build();
    }

    /**
     * Spring Security 使用基于 UserDetails 的身份验证
     *
     * @return
     */
    //@Bean
    //public UserDetailsService users() {
    //    UserDetails user = User.builder()
    //            .username("user")
    //            .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
    //            .roles("USER")
    //            .build();
    //    UserDetails admin = User.builder()
    //            .username("admin")
    //            .password("{bcrypt}$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO/BTk76klW")
    //            .roles("USER", "ADMIN")
    //            .build();
    //    return new InMemoryUserDetailsManager(user, admin);
    //}

    /**
     * 认证架构，主要类
     * <p>
     * SecurityContextHolder - SecurityContextHolder 是 Spring Security 存储 认证 用户细节的地方。
     * <p>
     * SecurityContext - 是从 SecurityContextHolder 获得的，包含了当前认证用户的 Authentication （认证）。
     * <p>
     * Authentication - 可以是 AuthenticationManager 的输入，以提供用户提供的认证凭证或来自 SecurityContext 的当前用户。
     * <p>
     * GrantedAuthority - 在 Authentication （认证）中授予委托人的一种权限（即role、scope等）。
     * <p>
     * AuthenticationManager - 定义 Spring Security 的 Filter 如何执行 认证 的API。
     * <p>
     * ProviderManager - 最常见的 AuthenticationManager 的实现。
     * <p>
     * AuthenticationProvider - 由 ProviderManager 用于执行特定类型的认证。
     * <p>
     * 用 AuthenticationEntryPoint 请求凭证 - 用于从客户端请求凭证（即重定向到登录页面，发送 WWW-Authenticate 响应，等等）。
     * <p>
     * AbstractAuthenticationProcessingFilter - 一个用于认证的基本 Filter。这也让我们很好地了解了认证的高层流程以及各部分是如何协作的。
     */
    AbstractAuthenticationProcessingFilter getLoginFilter() {
        JsonAuthenticationFilter loginFilter = new JsonAuthenticationFilter();
        loginFilter.setFilterProcessesUrl("/user/auth/login");
        // 配置 AuthenticationManager
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(inMemoryUserDetailsManager());
        daoAuthenticationProvider.setUserDetailsPasswordService(inMemoryUserDetailsManager());
        loginFilter.setAuthenticationManager(new ProviderManager(daoAuthenticationProvider));

        // 认证成功行为
        loginFilter.setAuthenticationSuccessHandler((req, resp, auth) -> {
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(200);
            PrintWriter out = resp.getWriter();
            Map<String,Object> respMsg = new HashMap<>();
            respMsg.put("msg", "认证成功!");
            respMsg.put("data", auth);
            out.write(new ObjectMapper().writeValueAsString(respMsg));
            out.flush();
            out.close();
        });
        // 认证失败行为
        loginFilter.setAuthenticationFailureHandler((req, resp, auth) -> {
            resp.setContentType("application/json;charset=utf-8");
            resp.setStatus(401);
            PrintWriter out = resp.getWriter();
            Map<String,String> respMsg = new HashMap<>();
            respMsg.put("msg", "认证失败!");
            out.write(new ObjectMapper().writeValueAsString(respMsg));
            out.flush();
            out.close();
        });
        return loginFilter;
    }

    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager() {
        UserDetails user = User.builder()
                .username("user")
                .password("{bcrypt}$2a$10$R4dqRuBXvmJ4SHq3h.KeU.RKSYvWkELibNskAx/4BfOfsQyJKwKoy")
                .roles("USER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{bcrypt}$2a$10$YO9I6Uk4E.iIF2TEur71FuNuJKZyGMKvaLsL2RZi8EOwf/g/Y1Nzy")
                .roles("USER", "ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, admin);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
