//package cn.catguild.ztest.springscurity;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.oauth2.jwt.JwtClaimsSet;
//import org.springframework.security.oauth2.jwt.JwtEncoder;
//import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
//import org.springframework.util.Base64Utils;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import java.nio.charset.StandardCharsets;
//import java.security.NoSuchAlgorithmException;
//import java.time.Instant;
//import java.util.Base64;
//import java.util.stream.Collectors;
//
///**
// * @author xiyan
// * @date 2023/4/13 17:57
// */
//@RestController
//public class JwtTestController {
//
//    @Autowired
//    JwtEncoder encoder;
//
//    @PostMapping("/token")
//    public String token(Authentication authentication) {
//        Instant now = Instant.now();
//        long expiry = 36000L;
//        // @formatter:off
//        String scope = authentication.getAuthorities().stream()
//                .map(GrantedAuthority::getAuthority)
//                .collect(Collectors.joining(" "));
//        JwtClaimsSet claims = JwtClaimsSet.builder()
//                .issuer("self")
//                .issuedAt(now)
//                .expiresAt(now.plusSeconds(expiry))
//                .subject(authentication.getName())
//                .claim("scope", scope)
//                .build();
//        // @formatter:on
//        return this.encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
//    }
//
//    //public static void main(String[] args) {
//    //    System.out.println(Base64Utils.encodeToString("catguild-huaxiexiyan-yizhanqingfeng".getBytes(StandardCharsets.UTF_8)));
//    //}
//    public static SecretKey generate() {
//        try {
//            // 使用AES算法生成256位的SecretKey
//            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//            keyGenerator.init(256);
//            return keyGenerator.generateKey();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public static void main(String[] args) {
//        SecretKey secretKey = generate();
//        System.out.println(Base64.getEncoder().encodeToString(secretKey.getEncoded()));
//    }
//
//}
