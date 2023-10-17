package cn.catguild.business.erp.presentation.model;

import cn.catguild.business.erp.infrastructure.domain.type.AfterSalesStatus;
import cn.catguild.business.erp.infrastructure.domain.type.DeliveryStatus;
import cn.catguild.business.erp.infrastructure.domain.type.OrderStatus;
import cn.catguild.business.erp.infrastructure.domain.type.PaymentMethod;
import cn.catguild.business.erp.presentation.converter.*;
import cn.catguild.common.type.YesNoStatus;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 订单导入
 *
 * @author xiyan
 * @date 2023/10/8 22:06
 */
@Data
public class OnlineOrderImportRequest {

    // 订单基本信息
    @ExcelProperty("订单号")
    private String orderNum;

    @ExcelProperty(value = "订单状态", converter = OrderStatusConverter.class)
    private OrderStatus orderStatus;

    @ExcelProperty(value = "售后状态", converter = AfterSalesStatusConverter.class)
    private AfterSalesStatus afterSalesStatus;

    @ExcelProperty("下单时间")
    private LocalDateTime orderPlacedTime;

    @ExcelProperty("支付时间")
    private LocalDateTime paymentTime;

    @ExcelProperty("发货时间")
    private LocalDateTime shipTime;

    @ExcelProperty("确认收货时间")
    private LocalDateTime receivedTime;

    @ExcelProperty("订单成交时间")
    private LocalDateTime orderCompletionTime;

    @ExcelProperty("预约配送时间")
    private LocalDateTime scheduledDeliveryTime;

    @ExcelProperty(value = "是否审核中", converter = YesNoStatusConverter.class)
    private YesNoStatus isUnderReview;

    @ExcelProperty(value = "是否抽奖或0元试用", converter = YesNoStatusConverter.class)
    private YesNoStatus isLuckyDrawOrZeroCostTrial;

    @ExcelProperty(value = "是否顺丰加价", converter = YesNoStatusConverter.class)
    private YesNoStatus isSFExpressSurcharge;

    @ExcelProperty(value = "是否门店自提", converter = YesNoStatusConverter.class)
    private YesNoStatus isInStorePickup;

    @ExcelProperty(value = "是否节能补贴", converter = YesNoStatusConverter.class)
    private YesNoStatus isEnergyEfficiencySubsidy;

    @ExcelProperty(value = "是否分期", converter = YesNoStatusConverter.class)
    private YesNoStatus isInstallment;

    @ExcelProperty(value = "分期期数", converter = YesNoStatusConverter.class)
    private YesNoStatus installmentPeriods;

    @ExcelProperty(value = "手续费承担方", converter = YesNoStatusConverter.class)
    private YesNoStatus feeBearer;

    @ExcelProperty(value = "是否无痕发货", converter = YesNoStatusConverter.class)
    private YesNoStatus isDiscreetShipping;

    @ExcelProperty("订单来源")
    private String orderSource;

    @ExcelProperty(value = "是否社区团购", converter = YesNoStatusConverter.class)
    private YesNoStatus isCommunityGroupPurchase;

    @ExcelProperty("小区送达情况")
    private String communityDeliveryStatus;

    @ExcelProperty(" 用户购买手机号")
    private String userPhoneNumber;

    @ExcelProperty("海淘清关订单号")
    private String crossBorderOrderNumber;

    // 订单商品明细
    @ExcelProperty("商品id")
    private Long spuId;
    @ExcelProperty("商品")
    private String spuName;
    @ExcelProperty("样式ID")
    private Long skuId;
    @ExcelProperty("商品规格")
    private String skuName;
    @ExcelProperty("商品数量(件)")
    private BigDecimal skuQuantity;
    @ExcelProperty("商品一级类目")
    private String productCategoryLevel1;
    @ExcelProperty("商品二级类目")
    private String productCategoryLevel2;
    @ExcelProperty("商品三级类目")
    private String productCategoryLevel3;
    @ExcelProperty("商品四级类目")
    private String productCategoryLevel4;
    @ExcelProperty("商家编码-规格维度")
    private String merchantCodeSku;
    @ExcelProperty("商家编码-商品维度")
    private String merchantCodeSpu;

    //赠品信息
    @ExcelProperty("赠品ID")
    private Long giftID;
    @ExcelProperty("赠品名称")
    private String giftName;
    @ExcelProperty("赠品规格")
    private String giftSpec;
    @ExcelProperty("赠品数量")
    private BigDecimal giftQuantity;
    // 其他信息
    @ExcelProperty("商家备注")
    private String merchantNote;
    @ExcelProperty("买家留言")
    private String buyerMessage;

    // 价格信息
    @ExcelProperty("商品总价(元)")
    private BigDecimal totalPrice;

    @ExcelProperty("邮费(元)")
    private BigDecimal shippingFee;

    @ExcelProperty("店铺优惠折扣(元)")
    private BigDecimal storeDiscount;

    @ExcelProperty("平台优惠折扣(元)")
    private BigDecimal platformDiscount;

    @ExcelProperty("多多支付立减金额(元)")
    private BigDecimal pddPaymentDiscount;

    @ExcelProperty("上门安装费(元)")
    private BigDecimal installationFee;

    @ExcelProperty("送货入户费(元)")
    private BigDecimal homeDeliveryFee;

    @ExcelProperty("送货入户并安装费(元)")
    private BigDecimal homeDeliveryAndInstallationFee;

    // 支付信息
    @ExcelProperty("用户实付金额(元)")
    private BigDecimal buyerPaidPayment;

    @ExcelProperty("商家实收金额(元)")
    private BigDecimal actualMerchantRevenue;

    @ExcelProperty(value = "支付方式", converter = PaymentMethodConverter.class)
    private PaymentMethod paymentMethod;

    // 配送信息
    @ExcelProperty("快递单号")
    private String trackingNumber;

    @ExcelProperty("快递公司")
    private String courierCompany;

    @ExcelProperty("省")
    private String province;

    @ExcelProperty("市")
    private String city;

    @ExcelProperty("区")
    private String district;

    @ExcelProperty("街道/镇")
    private String streetTown;

    @ExcelProperty("配送员手机号")
    private String courierPhoneNumber;

    @ExcelProperty(value = "是否已开始配送", converter = YesNoStatusConverter.class)
    private YesNoStatus isDeliveryStarted;

    @ExcelProperty(value = "配送员是否已送达", converter = YesNoStatusConverter.class)
    private YesNoStatus isCourierDelivered;

    @ExcelProperty(value = "配送状态", converter = DeliveryStatusConverter.class)
    private DeliveryStatus deliveryStatus;

    @ExcelProperty(value = "同城是否普通快递", converter = YesNoStatusConverter.class)
    private YesNoStatus localExpressOrRegularExpress;


    //商品,订单号,订单状态,商品数量(件),
    //是否审核中,支付时间,承诺发货时间,
    //承诺送达时间,发货时间,确认收货时间,
    //商品id,商品规格,样式ID,
    //商品一级类目,商品二级类目,商品三级类目,商品四级类目,
    //商家编码-规格维度,商家编码-商品维度,商家备注,买家留言,
    //售后状态,消费者资料,订单成交时间,消费者资料上传时间,
    //赠品名称,赠品id,赠品规格,赠品数量,
    //团ID,团身份,
    //是否已开始配送,配送员是否已送达,配送状态,同城是否普通快递,

    //商品总价(元),邮费(元),店铺优惠折扣(元),平台优惠折扣(元),多多支付立减金额(元),上门安装费(元),
    // 送货入户费(元),送货入户并安装费(元),用户实付金额(元),商家实收金额(元),

    //快递单号,快递公司,省,市,区,街道/镇,配送员手机号,
    //是否抽奖或0元试用,是否顺丰加价,用户购买手机号,
    //旅行类信息,充值号码,是否门店自提,门店名称,门店自定义编码,
    //关联货品编码,货品名称,货品类型,子货品,仓库名称,仓库所在地址,
    //海淘清关订单号,支付ID,支付方式,保税仓,是否直播间成交,是否直播间引导成交,
    //预约配送时间,是否节能补贴,是否分期,分期期数,手续费承担方,
    //是否无痕发货,订单来源,是否社区团购,小区送达情况,分期方式
}
