package cn.catguild.auth.domain.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiyan
 * @date 2023/9/19 11:14
 */
@AllArgsConstructor
@Getter
public enum NetworkAppType {

    /**
     * 阿里云盘
     */
    ALI_YUN_DRIVE("阿里云盘");

    private final String desc;

}
