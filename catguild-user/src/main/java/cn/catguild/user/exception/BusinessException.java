package cn.catguild.user.exception;

/**
 * @author xiyan
 * @date 2023/2/25 13:17
 */
public class BusinessException extends RuntimeException {

	private final int code;

	private final String msg;

	public BusinessException(String msg) {
		this(400, msg);
	}

	public BusinessException(int code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}
}
