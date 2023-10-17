package cn.catguild.business.erp.infrastructure.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author xiyan
 * @date 2023/10/9 21:47
 */
@Getter
@AllArgsConstructor
public enum PaymentMethod {
    YES(true,""),

    NO(false,"");

    private final boolean code;

    private final String chineseChar;

    public static PaymentMethod parseChineseChar(String chineseChar) {
        for (PaymentMethod status : PaymentMethod.values()) {
            if (StringUtils.pathEquals(status.getChineseChar(), chineseChar)) {
                return status;
            }
        }
        return null;
    }

}
