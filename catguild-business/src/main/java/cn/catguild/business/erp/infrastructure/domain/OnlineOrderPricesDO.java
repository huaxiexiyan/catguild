package cn.catguild.business.erp.infrastructure.domain;

import cn.catguild.common.entity.jpa.BaseTenant;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

/**
 * @author xiyan
 * @date 2023/10/9 22:04
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`business_erp_online_order_prices`")
public class OnlineOrderPricesDO extends BaseTenant {

    @Comment("线上订单ID")
    private Long orderId;

    @Comment("商品总价")
    private BigDecimal totalPrice;

    @Comment("邮费")
    private BigDecimal shippingFee;

    @Comment("店铺优惠折扣")
    private BigDecimal storeDiscount;

    @Comment("平台优惠折扣")
    private BigDecimal platformDiscount;

    @Comment("多多支付立减金额")
    private BigDecimal pddPaymentDiscount;

    @Comment("上门安装费")
    private BigDecimal installationFee;

    @Comment("送货入户费")
    private BigDecimal homeDeliveryFee;

    @Comment("送货入户并安装费")
    private BigDecimal homeDeliveryAndInstallationFee;


}
