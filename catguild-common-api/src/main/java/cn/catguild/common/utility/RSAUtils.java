package cn.catguild.common.utility;

import com.google.common.io.Files;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author xiyan
 * @date 2023/8/15 14:00
 */
@Slf4j
public class RSAUtils {

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    private static void generateRsaKeyFile() {
        try {
            KeyPair keyPair = generateRsaKey();
            RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
            RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
            Files.write( publicKey.getEncoded(),new File("public_key"));
            Files.write( privateKey.getEncoded(),new File("private_key"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PublicKey getPublicKey(String path) {
        try {
            File file = new File(path);
            log.info("加载RSA的公钥文件【{}】",file.getAbsolutePath());
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Files.toByteArray(file));
            return KeyFactory.getInstance("RSA").generatePublic(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static PrivateKey getPrivateKey(String path) {
        try {
            File file = new File(path);
            log.info("加载RSA的私钥文件【{}】",file.getAbsolutePath());
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Files.toByteArray(file));
            return KeyFactory.getInstance("RSA").generatePrivate(keySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    //public static void main(String[] args) {
    //    generateRsaKeyFile();
    //    getPublicKey("public_key");
    //    getPrivateKey("private_key");
    //}

}
