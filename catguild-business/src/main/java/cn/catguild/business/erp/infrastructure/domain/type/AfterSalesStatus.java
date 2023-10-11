package cn.catguild.business.erp.infrastructure.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author xiyan
 * @date 2023/10/9 22:28
 */
@Getter
@AllArgsConstructor
public enum AfterSalesStatus{

    PROCESSING("售后处理中"),

    REFUND_SUCCESS("退款成功"),

    NO_AFTER_SALES_OR_CANCELLED("无售后或售后取消");

    private final String chineseChar;

    public static AfterSalesStatus parseChineseChar(String chineseChar) {
        for (AfterSalesStatus status : AfterSalesStatus.values()) {
            if (StringUtils.pathEquals(status.getChineseChar(), chineseChar)) {
                return status;
            }
        }
        return null;
    }

}
