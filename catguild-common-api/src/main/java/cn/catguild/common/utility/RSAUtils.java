package cn.catguild.common.utility;

import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.util.StreamUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author xiyan
 * @date 2023/8/15 14:00
 */
@Slf4j
public class RSAUtils {

    private static void generateRsaKeyFile(String algorithm, String pubPath, String priPath) throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        Files.write(Base64.getMimeEncoder().encode(publicKey.getEncoded()), new File(pubPath));
        Files.write(Base64.getMimeEncoder().encode(privateKey.getEncoded()), new File(priPath));
    }

    public static PublicKey getPublicKey(String pubPath) {
        try {
            File file = new File(pubPath);
            log.info("加载RSA的公钥文件【{}】", file.getAbsolutePath());
            String publicKeyValue = StreamUtils.copyToString(new FileInputStream(file), StandardCharsets.UTF_8);
            return KeyFactory.getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(getPublicKeySpec(publicKeyValue)));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            log.error("加载RSA的公钥文件失败");
            throw new RuntimeException(e);
        }
    }

    public static PublicKey getPublicKeyFromResource(Resource pubResource) {
        try {
            log.info("加载RSA的公钥文件【{}】", pubResource.getURL());
            String publicKeyValue = StreamUtils.copyToString(pubResource.getInputStream(), StandardCharsets.UTF_8);
            return KeyFactory.getInstance("RSA")
                    .generatePublic(new X509EncodedKeySpec(getPublicKeySpec(publicKeyValue)));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            log.error("加载RSA的公钥文件失败");
            throw new RuntimeException(e);
        }
    }

    public static PrivateKey getPrivateKey(String priPath) {
        try {
            File file = new File(priPath);
            log.info("加载RSA的私钥文件【{}】", file.getAbsolutePath());
            String privateKeyValue = StreamUtils.copyToString(new FileInputStream(file), StandardCharsets.UTF_8);
            return KeyFactory.getInstance("RSA")
                    .generatePrivate(new PKCS8EncodedKeySpec(getPrivateKeySpec(privateKeyValue)));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            log.error("加载RSA的私钥文件失败");
            throw new RuntimeException(e);
        }
    }

    public static PrivateKey getPrivateKeyFromResource(Resource priResource) {
        try {
            log.info("加载RSA的私钥文件【{}】", priResource.getURL());
            String privateKeyValue = StreamUtils.copyToString(priResource.getInputStream(), StandardCharsets.UTF_8);
            return KeyFactory.getInstance("RSA")
                    .generatePrivate(new PKCS8EncodedKeySpec(getPrivateKeySpec(privateKeyValue)));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            log.error("加载RSA的私钥文件失败");
            throw new RuntimeException(e);
        }
    }


    private static byte[] getPublicKeySpec(String keyValue) {
        keyValue = keyValue.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "");
        return Base64.getMimeDecoder().decode(keyValue);
    }

    private static byte[] getPrivateKeySpec(String keyValue) {
        keyValue = keyValue.replace("-----BEGIN PRIVATE KEY-----", "")
                .replace("-----END PRIVATE KEY-----", "");
        return Base64.getMimeDecoder().decode(keyValue);
    }

    public static void main(String[] args) throws Exception {
        generateRsaKeyFile("RSA", "public_key.pub", "private_key.pri");
        //getPublicKey("public_key");
        //getPrivateKey("private_key");
    }



}
