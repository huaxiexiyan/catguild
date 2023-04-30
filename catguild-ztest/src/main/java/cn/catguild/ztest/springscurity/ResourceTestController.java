package cn.catguild.ztest.springscurity.helloword;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * http://localhost:21000/resource/auth
 *
 * @author xiyan
 * @date 2023/4/13 10:51
 */
@RequestMapping("/resource")
@RestController
public class ResourceTestController {

	@GetMapping("/auth")
	public String testAuth(){
		return "访问成功";
	}

}
