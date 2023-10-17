package cn.catguild.business.erp.infrastructure.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author xiyan
 * @date 2023/10/9 22:00
 */
@Getter
@AllArgsConstructor
public enum DeliveryStatus {
    YES(true,""),

    NO(false,"");

    private final boolean code;

    private final String chineseChar;

    public static DeliveryStatus parseChineseChar(String chineseChar) {
        for (DeliveryStatus status : DeliveryStatus.values()) {
            if (StringUtils.pathEquals(status.getChineseChar(), chineseChar)) {
                return status;
            }
        }
        return null;
    }

}
