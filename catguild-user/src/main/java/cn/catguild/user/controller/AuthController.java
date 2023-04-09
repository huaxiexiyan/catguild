package cn.catguild.user.controller;

import cn.catguild.user.service.AccountService;
import cn.catguild.user.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xiyan
 * @date 2022/10/14 16:18
 */
@RequestMapping
@RestController
@AllArgsConstructor
public class AuthController {

	private final AccountService accountService;

	private final AuthService authService;


	//public static void main(String[] args) {
	//	// 密钥
	//	String id = "ES256";
	//	KeyPair keyPair = KeyUtil.generateKeyPair(AlgorithmUtil.getAlgorithm(id));
	//	//生成公私钥对
	//	PrivateKey privateKey = keyPair.getPrivate();
	//	PublicKey publicKey = keyPair.getPublic();
	//	//获得私钥
	//	String privateKeyStr = bytesToBase64(privateKey.getEncoded());
	//	System.out.println("私钥：\n" + privateKeyStr);
	//	//获得公钥
	//	String publicKeyStr = bytesToBase64(publicKey.getEncoded());
	//	System.out.println("公钥：\n" + publicKeyStr);
	//
	//	// SHA256withRSA
	//	JWTSigner signer = JWTSignerUtil.createSigner(id, keyPair);
	//
	//
	//	String token = JWT.create()
	//		.setHeader("alg","ES256")
	//		.setPayload("sub", "1234567890")
	//		.setPayload("name", "loolycccc")
	//		.setPayload("admin", true)
	//		.setSigner(signer)
	//		.sign();
	//
	//
	//	System.out.println("token:\n"+token);
	//	System.out.println(JWTUtil.verify(token,signer));
	//}

	//@PostMapping("/login")
	//public ApiResponse<?> login(@RequestBody Account account){
	//	account = accountService.login(account.getUsername(), account.getPassword());
	//	//LoginAuth loginAuth= new LoginAuth(JWTUtil.createToken(new HashMap<>(),new HashMap<>(), JWTSignerUtil.createSigner()));
	//
	//	// 密钥
	//	byte[] key = "1234567890".getBytes();
	//
	//	// SHA256withRSA
	//	String id = "ES256";
	//	JWTSigner signer = JWTSignerUtil.createSigner(id,
	//		// 随机生成密钥对，此处用户可自行读取`KeyPair`、公钥或私钥生成`JWTSigner`
	//		KeyUtil.generateKeyPair(AlgorithmUtil.getAlgorithm(id)));
	//
	//	String token = JWT.create()
	//		.setPayload("sub", "1234567890")
	//		.setPayload("name", "looly")
	//		.setPayload("admin", true)
	//		.setSigner(signer)
	//		.sign();
	//
	//
	//	return ApiResponse.ok();
	//}

	/**
	 * 字节数组转Base64编码
	 *
	 * @param bytes 字节数组
	 * @return Base64编码
	 */
	//private static String bytesToBase64(byte[] bytes) {
	//	byte[] encodedBytes = Base64.getEncoder().encode(bytes);
	//	return new String(encodedBytes, StandardCharsets.UTF_8);
	//}
	//
	///**
	// * Base64编码转字节数组
	// *
	// * @param base64Str Base64编码
	// * @return 字节数组
	// */
	//private static byte[] base64ToBytes(String base64Str) {
	//	byte[] bytes = base64Str.getBytes(StandardCharsets.UTF_8);
	//	return Base64.getDecoder().decode(bytes);
	//}
	//
	///**
	// * 登录
	// *
	// * @param account
	// * @return
	// */
	//@PostMapping("/login")
	//public Map<String, String> login(@RequestBody Account account) {
	//	Map<String, String> map = new HashMap<>();
	//	map.put("token", "dasjoidjaiosdkjio");
	//	authService.login(1, account);
	//	Map<String, String> resultMap = new HashMap<>();
	//	resultMap.put("status","ok");
	//	resultMap.put("currentAuthority","admin");
	//	return resultMap;
	//}
	//
	//
	///**
	// * 获取当前登录用户的信息
	// *
	// * @return
	// */
	//@GetMapping("/currentUser")
	//public ApiResponse<?> getUserInfo() {
	//	Map<String, Object> map = new HashMap<>();
	//	map.put("name", "admin");
	//	map.put("userid", "00000001");
	//	map.put("avatar", "https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
	//	return ApiResponse.ok(map);
	//}
	//
	///**
	// * 登出
	// *
	// * @return
	// */
	//@PostMapping("/logout")
	//public ApiResponse<?> logout() {
	//	return ApiResponse.ok();
	//}

}
