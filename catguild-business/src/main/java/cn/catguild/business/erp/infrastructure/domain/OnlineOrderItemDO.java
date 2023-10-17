package cn.catguild.business.erp.infrastructure.domain;

import cn.catguild.business.erp.infrastructure.domain.type.AfterSalesStatus;
import cn.catguild.common.entity.jpa.BaseTenantSub;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

import java.math.BigDecimal;

/**
 * @author xiyan
 * @date 2023/10/9 21:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`business_erp_online_order_item`")
@Comment("在线订单行")
@TableName("business_erp_online_order_item")
public class OnlineOrderItemDO extends BaseTenantSub {

    @Comment("订单id")
    @Column(nullable = false)
    private Long orderId;
    @Comment("订单号")
    @Column(nullable = false)
    private String orderNum;

    // 商品信息
    @Comment("商品ID")
    private Long spuId;
    @Comment("商品名称")
    private String spuName;
    @Comment("商品规格ID")
    private Long skuId;
    @Comment("商品sku名字")
    private String skuName;
    @Comment("商品sku价格")
    private BigDecimal skuPrice;
    @Comment("商品购买的数量")
    private BigDecimal skuQuantity;
    @Comment("商品一级类目")
    private String productCategoryLevel1;
    @Comment("商品二级类目")
    private String productCategoryLevel2;
    @Comment("商品三级类目")
    private String productCategoryLevel3;
    @Comment("商品四级类目")
    private String productCategoryLevel4;
    @Comment("商家编码-规格维度")
    private String merchantCodeSku;
    @Comment("商家编码-商品维度")
    private String merchantCodeSpu;

    //赠品信息
    @Comment("赠品ID")
    private Long giftID;
    @Comment("赠品名称")
    private String giftName;
    @Comment("赠品规格")
    private String giftSpec;
    @Comment("赠品数量")
    private BigDecimal giftQuantity;

    // 售后信息
    @Comment("售后状态")
    @Enumerated(EnumType.STRING)
    private AfterSalesStatus afterSalesStatus;

    // 其他信息
    @Comment("商家备注")
    private String merchantNote;
    @Comment("买家留言")
    private String buyerMessage;


}
