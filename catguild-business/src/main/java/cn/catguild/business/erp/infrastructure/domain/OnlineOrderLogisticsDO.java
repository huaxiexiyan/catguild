package cn.catguild.business.erp.infrastructure.domain;

import cn.catguild.business.erp.infrastructure.domain.type.DeliveryStatus;
import cn.catguild.common.entity.jpa.BaseTenant;
import cn.catguild.common.type.YesNoStatus;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.Comment;

/**
 * 物流信息
 *
 * @author xiyan
 * @date 2023/10/9 21:55
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "`business_erp_online_order_logistics`")
@Comment("在线订单物流信息")
@TableName("business_erp_online_order_logistics")
public class OnlineOrderLogisticsDO extends BaseTenant {

    @Comment("线上订单id")
    @Column(nullable = false)
    private Long orderId;

    @Comment("线上订单号")
    @Column(nullable = false)
    private String orderNum;

    @Comment("快递单号")
    private String trackingNumber;

    @Comment("快递公司")
    private String courierCompany;

    @Comment("省")
    private String province;

    @Comment("市")
    private String city;

    @Comment("区")
    private String district;

    @Comment("街道/镇")
    private String streetTown;

    @Comment("配送员手机号")
    private String courierPhoneNumber;

    @Comment("是否已开始配送")
    private YesNoStatus isDeliveryStarted;

    @Comment("配送员是否已送达")
    private YesNoStatus isCourierDelivered;

    @Comment("配送状态")
    private DeliveryStatus deliveryStatus;

    @Comment("同城是否普通快递")
    private YesNoStatus localExpressOrRegularExpress;

    @Comment("海淘清关订单号")
    private String crossBorderOrderNumber;


}
