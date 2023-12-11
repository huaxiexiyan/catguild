package cn.catguild.business.erp.domain;

import lombok.Data;

import java.util.List;

/**
 * @author xiyan
 * @date 2023/10/19 11:00
 */
@Data
public class ErpSpu {

    private Long id;

    private String name;

    private List<ErpSku> skus;

    /**
     * 关联线上 spuId
     */
    private Long onlineSpuId;

}
