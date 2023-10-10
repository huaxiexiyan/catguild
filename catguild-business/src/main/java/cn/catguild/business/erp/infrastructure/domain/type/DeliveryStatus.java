package cn.catguild.business.erp.infrastructure.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiyan
 * @date 2023/10/9 22:00
 */
@Getter
@AllArgsConstructor
public enum DeliveryStatus {
    YES(true),

    NO(false);

    private final boolean code;
}
