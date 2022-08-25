package cn.catguild.user.thirdparty.wechat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Lionel
 * @date 2022-08-25 22:44
 */
@Slf4j
@RequestMapping("/thirdparty/wechat")
@RestController
public class TestController {

	@GetMapping("/check")
	public Boolean check(@RequestParam Map<String,String> parameters) throws AesException {
		log.info("接口入参:{}", parameters);
		String signature = parameters.get("signature");
		String timestamp = parameters.get("timestamp");
		String nonce = parameters.get("nonce");
		String echoStr = parameters.get("echoStr");
		WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt("catguild", "", "wx87e0cdf060bc4717");
		wxBizMsgCrypt.verifyUrl(signature, timestamp, nonce, echoStr);
		return true;
	}

}
