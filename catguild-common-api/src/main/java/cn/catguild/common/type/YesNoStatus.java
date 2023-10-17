package cn.catguild.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author xiyan
 * @date 2023/8/7 11:19
 */
@Getter
@AllArgsConstructor
public enum YesNoStatus {

    YES(true, "是"),

    NO(false, "否");

    private final boolean code;

    private final String chineseChar;

    public static YesNoStatus parseChineseChar(String chineseChar) {
        for (YesNoStatus status : YesNoStatus.values()) {
            if (StringUtils.pathEquals(status.getChineseChar(), chineseChar)) {
                return status;
            }
        }
        return null;
    }

}
