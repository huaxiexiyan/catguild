package cn.catguild.common.utility;

import java.security.SecureRandom;

/**
 * @author xiyan
 * @date 2023/8/9 14:23
 */
public class RandomPasswordGenerator {

    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:,.<>?";

    private static final int PASSWORD_LENGTH = 32;

    /**
     * 默认生成的是32位无特殊字符的随机字符串
     *
     * @return
     */
    public static String generateRandomPassword(int length, String characters) {
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characters.length());
            char randomChar = characters.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    public static String generateRandomPassword(int length) {
        return generateRandomPassword(length, CHARACTERS);
    }

    public static String generateRandomPassword() {
        return generateRandomPassword(PASSWORD_LENGTH, CHARACTERS);
    }

    public static String generateRandomPassword64() {
        return generateRandomPassword(64);
    }

    //public static void main(String[] args) {
    //    String randomPassword = generateRandomPassword();
    //    System.out.println("Random Password: " + randomPassword);
    //}

}
