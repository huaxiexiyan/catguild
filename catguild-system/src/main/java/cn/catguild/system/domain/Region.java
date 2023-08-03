package cn.catguild.system.domain;

import lombok.Builder;
import lombok.Data;

/**
 * @author xiyan
 * @date 2023/5/21 10:52
 */
@Builder
@Data
public class Region {

    private String code;

    private String name;

    private Integer sort;

}
