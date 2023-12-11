package cn.catguild.business.erp.domain;

import cn.catguild.business.erp.domain.type.ErpAttr;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/19 11:20
 */
@Data
public class ErpSku {

    /**
     * spuId
     */
    private Long goodsId;

    /**
     * 规格列表
     */
    private List<ErpAttr> attrs;

    /**
     * 关联线上 skuId
     */
    private Long onlineSkuId;

    private BigDecimal purchasePrice;

}
