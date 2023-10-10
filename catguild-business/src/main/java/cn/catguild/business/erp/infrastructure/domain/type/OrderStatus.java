package cn.catguild.business.erp.infrastructure.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiyan
 * @date 2023/10/9 21:50
 */
@Getter
@AllArgsConstructor
public enum OrderStatus {

    YES(true),

    NO(false);

    private final boolean code;

}
