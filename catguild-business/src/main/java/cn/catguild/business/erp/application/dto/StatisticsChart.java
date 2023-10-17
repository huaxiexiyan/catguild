package cn.catguild.business.erp.application.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.LinkedHashMap;

/**
 * @author lionel
 * @date 2022/9/23 9:37
 */
@Data
public class StatisticsChart {

    /**
     * 本周、本月、上月、本年
     **/
    private TimeScale timeScale;

    /**
     * 统计类型（data中统计的数据）
     * 目前是：订单金额（默认）、订货单数、订货客户
     */
    private StatisticsType type;

    /**
     * 横轴显示-纵轴统计数值
     **/
    private LinkedHashMap<String, BigDecimal> data;

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


}
