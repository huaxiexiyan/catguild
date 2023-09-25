package cn.catguild.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiyan
 * @date 2023/9/19 11:30
 */
@AllArgsConstructor
@Getter
public enum AttributeType {

    STRING("字符串"),

    NUMBER("数值"),

    DATE("时间");

    private final String desc;

}
