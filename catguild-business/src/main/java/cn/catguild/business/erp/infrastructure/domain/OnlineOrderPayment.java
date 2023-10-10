package cn.catguild.business.erp.infrastructure.domain;

import cn.catguild.business.erp.infrastructure.domain.type.PaymentMethod;
import cn.catguild.common.entity.jpa.BaseTenant;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

/**
 * @author xiyan
 * @date 2023/10/9 21:31
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`business_erp_online_order_payment`")
public class OnlineOrderPayment extends BaseTenant {

    @Comment("订单ID")
    private Long orderId;

    @Comment("顾客实付金额")
    private BigDecimal buyerPaidPayment;

    @Comment("商家实收金额")
    private BigDecimal actualMerchantRevenue;

    @Comment("支付方式")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

}
