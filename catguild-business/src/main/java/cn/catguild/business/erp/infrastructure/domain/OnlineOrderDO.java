package cn.catguild.business.erp.infrastructure.domain;

import cn.catguild.business.erp.infrastructure.domain.type.AfterSalesStatus;
import cn.catguild.business.erp.infrastructure.domain.type.OrderStatus;
import cn.catguild.common.entity.jpa.BaseTenant;
import cn.catguild.common.type.YesNoStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/9 21:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`business_erp_online_order`")
public class OnlineOrderDO extends BaseTenant {

    // 订单基本信息
    @Comment("订单号")
    private String orderNum;

    @Comment("订单状态")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Comment("售后状态")
    @Enumerated(EnumType.STRING)
    private AfterSalesStatus afterSalesStatus;

    @Comment("下单时间")
    private LocalDateTime orderPlacedTime;

    @Comment("支付时间")
    private LocalDateTime paymentTime;

    @Comment("发货时间")
    private LocalDateTime shipTime;

    @Comment("确认收货时间")
    private LocalDateTime receivedTime;

    @Comment("订单成交时间")
    private LocalDateTime orderCompletionTime;

    @Comment("预约配送时间")
    private LocalDateTime scheduledDeliveryTime;

    @Comment("是否审核中")
    @Enumerated(EnumType.STRING)
    private YesNoStatus isUnderReview;

    @Comment("是否抽奖或0元试用")
    @Enumerated(EnumType.STRING)
    private YesNoStatus isLuckyDrawOrZeroCostTrial;

    @Comment("是否顺丰加价")
    @Enumerated(EnumType.STRING)
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

    @Comment(" 用户购买手机号")
    private String userPhoneNumber;

    @Comment("海淘清关订单号")
    private String crossBorderOrderNumber;

    // 订单商品明细
    @Transient
    private List<OnlineOrderItemDO> orderItems;

    // 价格信息
    @Transient
    private OnlineOrderPricesDO pricesInfo;

    // 支付信息
    @Transient
    private OnlineOrderPayment paymentInfo;

    // 配送信息
    @Transient
    private OnlineOrderLogisticsDO logisticsInfo;

}
