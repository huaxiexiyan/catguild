package cn.catguild.system.presentation;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xiyan
 * @date 2023/8/7 11:47
 */
@Slf4j
@RequestMapping("/webook")
@AllArgsConstructor
@RestController
public class DemoResource {

	@PostMapping("/network")
	public String getCurrentDomain(@RequestBody Map<String,Object> map) {
		log.info("执行: {}",map);
		return "Current domain: ";
	}

}
