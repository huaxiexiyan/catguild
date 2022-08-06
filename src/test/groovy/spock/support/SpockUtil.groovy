package spock.support

import org.springframework.core.io.ClassPathResource
import org.springframework.lang.Nullable

class SpockUtil {

    /**
     * 获取 resource 文件夹下的文件
     *
     * @param filePath 默认的根路径是 resource
     * @return 文件对象
     */
    static getResourceFile(String filePath) {
        def resource = new ClassPathResource(filePath)
        resource.getFile()
    }

    /**
     * 判断字符串是否为 null 或 “” 或 “ ”
     *
     * @param str 需要验证的字符
     * @return null 或 “” 或 “ ” 返回 true 否则返回 false
     */
    static boolean isBlank(String str) {
        return !isNotBlank(str);
    }

    static boolean isNotBlank(String str) {
        return hasText(str);
    }

    private static boolean hasText(@Nullable String str) {
        return (str != null && !str.isEmpty() && containsText(str));
    }

    private static boolean containsText(CharSequence str) {
        int strLen = str.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
