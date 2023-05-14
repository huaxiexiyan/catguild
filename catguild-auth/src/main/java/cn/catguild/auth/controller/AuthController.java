package cn.catguild.auth.controller;

import cn.catguild.auth.controller.command.LoginCommand;
import cn.catguild.auth.controller.vo.LoginVO;
import cn.catguild.common.api.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author xiyan
 * @date 2023/5/13 18:54
 */
@RestController
public class AuthController {

    @PostMapping("/login")
    public ApiResponse<LoginVO> login(@RequestBody LoginCommand loginCommand){
        LoginVO loginVO = new LoginVO();
        loginVO.setToken("fakeToken1");
        loginVO.setUsername("vben");
        loginVO.setRealName("Vben Admin");
        loginVO.setUserId(1L);
        return ApiResponse.ok(loginVO);
    }

    @GetMapping("/getUserInfo")
    public ApiResponse<LoginVO> getUserInfo(){
        LoginVO loginVO = new LoginVO();
        loginVO.setToken("fakeToken1");
        loginVO.setUsername("vben");
        loginVO.setRealName("Vben Admin");
        loginVO.setUserId(1L);
        return ApiResponse.ok(loginVO);
    }

    @GetMapping("/logout")
    public ApiResponse<Void> logout(){
        return ApiResponse.ok();
    }

    @GetMapping("/getPermCode")
    public ApiResponse<Void> getPermCode(){
        return ApiResponse.ok();
    }

}
