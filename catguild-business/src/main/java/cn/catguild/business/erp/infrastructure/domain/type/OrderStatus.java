package cn.catguild.business.erp.infrastructure.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.util.StringUtils;

/**
 * @author xiyan
 * @date 2023/10/9 21:50
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {
//待发货；待支付；未发货，退款成功；已发货，待签收；已发货，退款成功；已签收；已取消
    //售后处理中；退款成功，无售后或售后取消

    PENDING_SHIPMENT("待发货"),

    PENDING_PAYMENT("待支付"),

    NOT_SHIPPED_REFUND_SUCCESS("未发货，退款成功"),

    SHIPPED_PENDING_RECEIPT("已发货，待签收"),

    SHIPPED_REFUND_SUCCESS("已发货，退款成功"),

    RECEIVED("已签收"),

    CANCELLED("已取消");

    private final String chineseChar;

    public static OrderStatus parseChineseChar(String chineseChar) {
        for (OrderStatus status : OrderStatus.values()) {
            if (StringUtils.pathEquals(status.getChineseChar(), chineseChar)) {
                return status;
            }
        }
        return null;
    }

}
