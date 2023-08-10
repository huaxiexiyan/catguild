package cn.catguild.common.utility;

/**
 * 邀请码生成
 * 需要：
 * 1、唯一性
 * 2、id与code能相互解析转换
 * 3、需要一定程度的保密，不能被轻易解析出id来
 * 4、长度适中 6-10 位
 *
 * 生成方案：
 * ps：排除hash，容易碰撞
 *
 *
 * @author xiyan
 * @date 2023/8/9 17:38
 */
public class ShareCodeUtils {

    /**
     * 候选的码，去掉01，防止与O、I混淆
     */
    private final static String candidateStr = "23456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static String toCode(Long id){
        return null;
    }

    public static String toId(String code){
        return null;
    }

    public static void main(String[] args) {
        String code = toCode(1689201526414905344L);
        System.out.println(code);
        System.out.println(toId(code));
    }

}
