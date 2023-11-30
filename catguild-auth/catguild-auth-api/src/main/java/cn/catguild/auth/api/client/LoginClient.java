package cn.catguild.auth.api.client;

import cn.catguild.auth.api.dto.LoginDTO;
import cn.catguild.common.api.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author xiyan
 * @date 2023/11/24 15:32
 */
@FeignClient("login")
public interface LoginClient {

    @PostMapping("/oauth2/token")
    ApiResponse<LoginDTO> login(@RequestHeader("Authorization") String basicAuth,
                                @RequestBody LoginDTO loginDTO);

}
