package cn.catguild.user.thirdparty.wechat;

import cn.catguild.user.thirdparty.wechat.ase.AesException;
import cn.catguild.user.thirdparty.wechat.ase.WXBizMsgCrypt;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
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
		String echoStr = parameters.get("echostr");
		WXBizMsgCrypt wxBizMsgCrypt = new WXBizMsgCrypt("catguild", "", "wx87e0cdf060bc4717");
		wxBizMsgCrypt.verifyUrl(signature, timestamp, nonce, echoStr);
		return true;
	}

	@GetMapping("/check2")
	public String check2(@RequestParam Map<String,String> parameters) throws AesException {
		log.info("接口入参:{}", parameters);
		String signature = parameters.get("signature");
		String timestamp = parameters.get("timestamp");
		String nonce = parameters.get("nonce");
		String echoStr = parameters.get("echostr");
		ArrayList<String> list=new ArrayList<String>();
		list.add(nonce);
		list.add(timestamp);
		list.add("catguild");//这是第5步中你设置的Token
		Collections.sort(list);
		String sha1Singnature = DigestUtils.sha1Hex(list.get(0)+list.get(1)+list.get(2));
		if (sha1Singnature.equals(signature)){
			return echoStr;
		}else {
			return "";
		}
	}


}
