package cn.catguild.system.presentation;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2023/8/7 11:47
 */
@Slf4j
@RequestMapping("/system")
@AllArgsConstructor
@RestController
public class SystemResource {

	@RequestMapping("/client")
	public String getCurrentDomain(HttpServletRequest request) {
		String domain = request.getServerName();
		log.info("执行");
		return "Current domain: " + domain;
	}

}
