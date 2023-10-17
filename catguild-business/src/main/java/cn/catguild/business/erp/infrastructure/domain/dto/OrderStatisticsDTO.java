package cn.catguild.business.erp.infrastructure.domain.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author xiyan
 * @date 2023/10/16 16:37
 */
@Data
public class OrderStatisticsDTO {

    private LocalDate timeDay;

    /**
     * 图表侧边：销售额
     */
    private BigDecimal salesRevenue;

    /**
     * 图表侧边：订单量
     */
    private Long orderQuantity;

    /**
     * 图表侧边：销售量
     */
    private BigDecimal salesVolume;

    public void initZero() {
        this.salesRevenue = BigDecimal.ZERO;
        this.orderQuantity = 0L;
        this.salesVolume = BigDecimal.ZERO;
    }
}
