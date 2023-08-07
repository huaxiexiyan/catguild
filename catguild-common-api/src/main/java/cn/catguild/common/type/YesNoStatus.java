package cn.catguild.common.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author xiyan
 * @date 2023/8/7 11:19
 */
@Getter
@AllArgsConstructor
public enum YesNoStatus {

	YES(true),

	NO(false);

	private final boolean code;

}
