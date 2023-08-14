package cn.catguild.common.utility;

/**
 * 邀请码生成
 * id = 是个整数
 * code = 整数或大写字母都可以
 * 需要：
 * 1、唯一性
 * 2、id与code能相互解析转换
 * 3、生成的code需要一定程度的保密（规律不能太明显，轻易解析出id来）
 * 4、code长度适中 6-10 位
 * <p>
 * 生成方案：
 * 实现一个 双射函数 id空间 <-> code码空间
 * <p>
 * <p>
 * ps：排除hash，容易碰撞
 *
 * @author xiyan
 * @date 2023/8/9 17:38
 */
public class ShareCodeUtils {

    /**
     * 自定义进制(0,1没有加入,容易与o,l混淆)，数组顺序可进行调整增加反推难度，A用来补位因此此数组不包含A，共31个字符。
     */
    private static final char[] BASE = new char[]{'H', 'V', 'E', '8', 'S', '2', 'D', 'Z', 'X', '9', 'C', '7', 'P',
            '5', 'I', 'K', '3', 'M', 'J', 'U', 'F', 'R', '4', 'W', 'Y', 'L', 'T', 'N', '6', 'B', 'G', 'Q'};

    // 26 + 10 = 36 - 4= 32
    //private static final String CHARS = "23456789QWERTYUPASDFGHJKLZXCVBNM";
    private static final String CHARS = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";

    /**
     * 生成的code码长度
     * 原始映射空间 0 - BASE_NUMBER的CODE_LENGTH - 1
     * eg：32进制6位 = 1,073,741,824 - 1 = 1,073,741,823
     * <p>
     * 但考虑到混淆与扩散，值空间会比这个小
     */
    private static final int CODE_LENGTH = 6;

    private static final int SALT = 233;
    /**
     * 两个互质的数， ( id * m ) % p
     * 用来放大
     */
    private static final int PRIME1 = 7;
    private static final int PRIME2 = 5;

    private static final int BASE_NUMBER = CHARS.length();
    private static final int MAX_VALUE = ((int) Math.pow(32, 6) - 1) / PRIME1;

    //public static void main(String[] args) {
    //    String str = encode(38);
    //    System.out.println(decode(str));
    //}

    public static String encode(int id) {
        int[] b = new int[CODE_LENGTH];
        StringBuilder res = new StringBuilder();

        int pid = id * PRIME1 + SALT; // 扩大
        b[0] = pid;
        for (int i = 0; i < CODE_LENGTH - 1; i++) {
            b[i + 1] = b[i] / BASE_NUMBER;
            b[i] = (b[i] + i * b[0]) % BASE_NUMBER;
        }

        // 校验位
        for (int i = 0; i < CODE_LENGTH - 1; i++) {
            b[CODE_LENGTH - 1] += b[i];
        }
        b[CODE_LENGTH - 1] = (b[CODE_LENGTH - 1] * PRIME1) % CODE_LENGTH;

        for (int i = 0; i < CODE_LENGTH; i++) {
            res.append(CHARS.charAt(b[(i * PRIME2) % CODE_LENGTH])); // 洗牌
        }

        return res.toString();
    }

    public static int decode(String code) {
        if (code.length() != CODE_LENGTH) {
            return -1;
        }
        int[] b = new int[CODE_LENGTH];

        // 反洗牌
        for (int i = 0; i < CODE_LENGTH; i++) {
            b[(i * PRIME2) % CODE_LENGTH] = i;
        }

        // 转换回 CHARS 下标
        for (int i = 0; i < CODE_LENGTH; i++) {
            int j = CHARS.indexOf(code.charAt(b[i]));
            if (j == -1) {
                return -1; // 非法字符检查
            }
            b[i] = j;
        }

        // 校验
        int expect = 0;
        for (int i = 0; i < CODE_LENGTH - 1; i++) {
            expect += b[i];
        }
        expect = (expect * PRIME1) % CODE_LENGTH;
        if (b[5] != expect) {
            return -1;
        }

        // 反函数
        for (int i = CODE_LENGTH - 2; i >= 0; i--) {
            b[i] = (b[i] - i * (b[0] - BASE_NUMBER)) % BASE_NUMBER;
        }
        int res = 0;
        for (int i = CODE_LENGTH - 2; i > 0; i--) {
            res += b[i];
            res *= BASE_NUMBER;
        }

        // 反放大
        res = ((res + b[0]) - SALT) / PRIME1;
        return res;
    }

}
