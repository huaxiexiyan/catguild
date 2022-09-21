package cn.catguild.user.thirdparty.wechat.service.impl;

import cn.catguild.user.thirdparty.wechat.service.UserService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiyan
 * @date 2022/8/27 8:22
 */
@Slf4j
public class UserServiceImpl implements UserService {

	//public static void main(String[] args) throws IOException, InterruptedException, WxErrorException {
	//	WxMpDefaultConfigImpl config = new WxMpDefaultConfigImpl();
	//	config.setAppId("wx87e0cdf060bc4717"); // 设置微信公众号的appid
	//	config.setSecret("9752fe44b64b04be5c5ce68b3afa5ca3"); // 设置微信公众号的app corpSecret
	//	config.setToken("catguild"); // 设置微信公众号的token
	//	WxMpService wxService = new WxMpServiceImpl();// 实际项目中请注意要保持单例，不要在每次请求时构造实例，具体可以参考demo项目
	//	wxService.setWxMpConfigStorage(config);
	//	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	//	WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
	//		.toUser("opNG75-iAv-n5XLQ5MIwBNO47Hrg")
	//		.templateId("9u9BR7X3666BBMcbJRtCTDTpYqigZqVZx0LeTHDXwBo")
	//		.url(" ")
	//		.build();
	//	templateMessage.addData(new WxMpTemplateData("first", dateFormat.format(new Date()), "#FF00FF"))
	//		.addData(new WxMpTemplateData("remark", RandomStringUtils.randomAlphanumeric(100), "#FF00FF"));
	//	String msgId = wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
	//	System.out.println(msgId);
	//}

}
