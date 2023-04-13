package cn.catguild.user.exception;

import lombok.Getter;

/**
 * @author xiyan
 * @date 2023/2/25 13:17
 */
public class BizException extends RuntimeException {

	@Getter
	private final int code;

	@Getter
	private final String msg;

	public BizException(String msg) {
		this(400, msg);
	}

	public BizException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
}
