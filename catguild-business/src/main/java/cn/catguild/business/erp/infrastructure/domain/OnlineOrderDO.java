package cn.catguild.business.erp.infrastructure.domain;

import cn.catguild.business.erp.infrastructure.domain.type.AfterSalesStatus;
import cn.catguild.business.erp.infrastructure.domain.type.OrderPlatform;
import cn.catguild.business.erp.infrastructure.domain.type.OrderStatus;
import cn.catguild.business.erp.infrastructure.domain.type.PaymentMethod;
import cn.catguild.common.entity.jpa.BaseTenant;
import cn.catguild.common.type.YesNoStatus;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/9 21:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`business_erp_online_order`",
        uniqueConstraints = @UniqueConstraint(columnNames = {"orderPlatform", "orderNum"}))
@Comment("在线订单")
@TableName("business_erp_online_order")
public class OnlineOrderDO extends BaseTenant {

    @Comment("订单平台")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderPlatform orderPlatform;

    // 订单基本信息
    @Comment("订单号")
    @Column(nullable = false)
    private String orderNum;

    @Comment("订单状态")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Comment("售后状态")
    @Enumerated(EnumType.STRING)
    private AfterSalesStatus afterSalesStatus;

    @Comment("下单时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderPlacedTime;

    @Comment("支付时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime paymentTime;

    @Comment("发货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime shipTime;

    @Comment("确认收货时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime receivedTime;

    @Comment("订单成交时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime orderCompletionTime;

    @Comment("预约配送时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scheduledDeliveryTime;

    @Comment("是否审核中")
    @Enumerated(EnumType.STRING)
    private YesNoStatus isUnderReview;

    @Comment("是否抽奖或0元试用")
    @Enumerated(EnumType.STRING)
    private YesNoStatus isLuckyDrawOrZeroCostTrial;

    @Comment("是否顺丰加价")
    @Enumerated(EnumType.STRING)
    @Column(name = "is_s_f_express_surcharge")
    private YesNoStatus isSFExpressSurcharge;

    @Comment("是否门店自提")
    @Enumerated(EnumType.STRING)
    private YesNoStatus isInStorePickup;

    @Comment("是否节能补贴")
    @Enumerated(EnumType.STRING)
    private YesNoStatus isEnergyEfficiencySubsidy;

    @Comment("是否分期")
    @Enumerated(EnumType.STRING)
    private YesNoStatus isInstallment;

    @Comment("分期期数")
    @Enumerated(EnumType.STRING)
    private YesNoStatus installmentPeriods;

    @Comment("手续费承担方")
    @Enumerated(EnumType.STRING)
    private YesNoStatus feeBearer;

    @Comment("是否无痕发货")
    @Enumerated(EnumType.STRING)
    private YesNoStatus isDiscreetShipping;

    @Comment("订单来源")
    private String orderSource;

    @Comment("是否社区团购")
    @Enumerated(EnumType.STRING)
    private YesNoStatus isCommunityGroupPurchase;

    @Comment("小区送达情况")
    private String communityDeliveryStatus;

    @Comment("用户购买手机号")
    private String userPhoneNumber;

    @Comment("海淘清关订单号")
    private String crossBorderOrderNumber;

    // 订单商品明细
    @Transient
    @TableField(exist = false)
    private List<OnlineOrderItemDO> orderItems;

    // 价格信息
    @Comment("商品总价(元)")
    private BigDecimal totalPrice;

    @Comment("邮费(元)")
    private BigDecimal shippingFee;

    @Comment("店铺优惠折扣(元)")
    private BigDecimal storeDiscount;

    @Comment("平台优惠折扣(元)")
    private BigDecimal platformDiscount;

    @Comment("多多支付立减金额(元)")
    private BigDecimal pddPaymentDiscount;

    @Comment("上门安装费(元)")
    private BigDecimal installationFee;

    @Comment("送货入户费(元)")
    private BigDecimal homeDeliveryFee;

    @Comment("送货入户并安装费(元)")
    private BigDecimal homeDeliveryAndInstallationFee;

    // 支付信息
    @Comment("顾客实付金额(元)")
    private BigDecimal buyerPaidPayment;

    @Comment("商家实收金额(元)")
    private BigDecimal actualMerchantRevenue;

    @Comment("支付方式")
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    // 配送信息
    @Transient
    @TableField(exist = false)
    private OnlineOrderLogisticsDO logisticsInfo;

}
