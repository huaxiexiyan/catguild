package cn.catguild.user.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author xiyan
 * @date 2022-08-07 14:20
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BizException  extends RuntimeException{

	/**
	 * 错误类型代码
	 */
	private final String errorCode;
	/**
	 * 向用户显示消息
	 */
	private final String errorMessage;

	public BizException(String errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

}
