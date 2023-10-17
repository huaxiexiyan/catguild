package cn.catguild.business.erp.application.dto;

import lombok.Getter;

/**
 * 统计类型
 *
 * @author lionel
 * @date 2022/9/23 10:40
 */
@Getter
public enum StatisticsType {

    /**
     * 销售额（商户支付的实际金额）
     **/
    SALES_REVENUE,

    /**
     * 订单量
     **/
    ORDER_QUANTITY,

    /**
     * 销售量
     **/
    SALES_VOLUME,

}
