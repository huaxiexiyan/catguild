package cn.catguild.business.erp.application.dto;

import lombok.Data;

/**
 * @author lionel
 * @date 2022/9/22 18:31
 */
@Data
public class MainStatistics {

    /**
     * 售后单审核：包含售后单申请审核、退货待退回审核
     **/
    private Integer unConfirmAfterOrder;

    /**
     * 待财务审核：包含所有待财务确认的单据
     **/
    private Integer unConfirmPayRecord;

    /**
     * 待审核订单：当前待审核状态的订单数量
     **/
    private Integer auditedOrder;
    /**
     * 待收款订单：所有可收款的订单
     **/
    private Integer confirmedOrder;
    /**
     * 待发货订单：包含待发货和部分发货订单
     **/
    private Integer waitDeliverOrder;

    /**
     * 未认证客户：包含待认证、认证中的客户
     **/
    private Integer unauthorizedCustomer;


}
