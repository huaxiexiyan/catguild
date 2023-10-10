package cn.catguild.business.erp.infrastructure.domain;

import cn.catguild.business.erp.infrastructure.domain.type.AfterSalesStatus;
import cn.catguild.common.entity.jpa.BaseTenantSub;
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
 * @date 2023/10/9 21:08
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`business_erp_online_order_item`")
public class OnlineOrderItemDO extends BaseTenantSub {

    @Comment("订单id")
    private Long orderId;

    // 商品信息
    @Comment("商品ID")
    private Long productId;
    @Comment("商品规格ID")
    private String skuId;
    @Comment("商品规格")
    private String sku;
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
