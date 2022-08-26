package cn.catguild.user.thirdparty.check;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiyan
 * @date 2022-08-26 09:45
 */
@Slf4j
public class CheckScheduled {

	//@Scheduled(cron = "0/15 * *  * * ? ")

	//public static void main(String[] args) throws IOException, InterruptedException {
	//	HttpClient client = HttpClient.newHttpClient();
	//	HttpRequest request = HttpRequest.newBuilder()
	//		.uri(URI.create("https://www.5dm.app/mark/202207"))
	//		.build();
	//	HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
	//	Document document = Jsoup.parse(response.body());
	//	//log.info("{}",document);
	//	Elements elements = document.getElementsByClass("qv_tooltip");
	//	//log.info("{}",elements);
	//	StringBuilder sb = new StringBuilder();
	//	for (Element element : elements) {
	//		Elements elementsByClass = element.getElementsByClass("gv-title");
	//		//log.info("{}",elementsByClass);
	//		Elements elementsByIndexEquals = element.getElementsByIndexEquals(0);
	//		String attr = elementsByIndexEquals.attr("title");
	//		String tempStr = attr.substring(attr.indexOf(">") + 1, attr.lastIndexOf("</h4>"));
	//		log.info("{}",tempStr);
	//		sb.append(tempStr).append("/n");
	//	}
	//
	//	HttpRequest request2 = HttpRequest.newBuilder()
	//		.uri(URI.create("https://sctapi.ftqq.com/SCT54348T6RX3VRCgUQfzpIf25PIEB5Hs.send"))
	//		.header("Content-Type", "application/json")
	//		.POST(HttpRequest.BodyPublishers.ofString(sb.toString()))
	//		.build();
	//	HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());
	//	log.info("{}",response2);
	////	https://sctapi.ftqq.com/SCT54348T6RX3VRCgUQfzpIf25PIEB5Hs.send?title=messagetitle&desp=messagecontent
	//}

//	title: 消息标题，必填。最大长度为 32 。
	//desp: 消息内容，选填。支持 Markdown语法 ，最大长度为 32KB ,消息卡片截取前 30 显示。
	//short: 消息卡片内容，选填。最大长度64。如果不指定，将自动从desp中截取生成。
	//channel: 动态指定本次推送使用的消息通道，选填。如不指定，则使用网站上的消息通道页面设置的通道。支持最多两个通道，多个通道值用竖线|隔开。比如，同时发送服务号和企业微信应用消息通道，则使用 9|66 。通道对应的值如下：
	//方糖服务号=9
	//企业微信应用消息=66
	//Bark iOS=8
	//企业微信群机器人=1
	//钉钉群机器人=2
	//飞书群机器人=3
	//测试号=0
	//自定义=88
	//PushDeer=18
	//openid: 消息抄送的openid，选填。只支持测试号和企业微信应用消息通道。测试号的 openid 从测试号页面获得 ，多个 openid 用 , 隔开。企业微信应用消息通道的 openid 参数，内容为接收人在企业微信中的 UID（可在消息通道页面配置好该通道后通过链接查看） , 多个人请 | 隔开，即可发给特定人/多个人。不填则发送给通道配置页面的接收人。
	//
	//public static void main(String[] args) throws IOException, InterruptedException {
	//	HttpClient client = HttpClient.newHttpClient();
	//	//HttpRequest request2 = HttpRequest.newBuilder()
	//	//	.uri(URI.create("https://sctapi.ftqq.com/SCT54348T6RX3VRCgUQfzpIf25PIEB5Hs.send"))
	//	//	.header("Content-Type", "application/json")
	//	//	.POST(HttpRequest.BodyPublishers.ofString(sb.toString()))
	//	//	.build();
	//	HttpRequest request2 = HttpRequest.newBuilder()
	//		.uri(URI.create("https://sctapi.ftqq.com/SCT54348T6RX3VRCgUQfzpIf25PIEB5Hs.send?title=测试&desp=测试消息"))
	//		.GET()
	//		.build();
	//	ObjectMapper objectMapper = new ObjectMapper();
	//	Map<String,String> map = new HashMap<>();
	//	map.put("title","番剧更新情报");
	//	map.put("desp","共有"+3+"部番剧更新了");
	//	map.put("short","海贼王【更新第三集】</b>死神【更新第四集】");
	//	map.put("channel","18");
	//	String requestBody = objectMapper
	//		.writerWithDefaultPrettyPrinter()
	//		.writeValueAsString(map);
	//	HttpRequest request3 = HttpRequest.newBuilder()
	//		.uri(URI.create("https://sctapi.ftqq.com/SCT54348T6RX3VRCgUQfzpIf25PIEB5Hs.send"))
	//		.header("Content-Type", "application/json")
	//		.POST(HttpRequest.BodyPublishers.ofString(requestBody))
	//		.build();
	//	HttpResponse<String> response2 = client.send(request3, HttpResponse.BodyHandlers.ofString());
	//	log.info("{}",response2);
	//
	////	https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN
	//}
}
