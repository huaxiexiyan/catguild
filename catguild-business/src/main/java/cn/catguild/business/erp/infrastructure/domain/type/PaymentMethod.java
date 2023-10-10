package cn.catguild.business.erp.infrastructure.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiyan
 * @date 2023/10/9 21:47
 */
@Getter
@AllArgsConstructor
public enum PaymentMethod {
    YES(true),

    NO(false);

    private final boolean code;
}
