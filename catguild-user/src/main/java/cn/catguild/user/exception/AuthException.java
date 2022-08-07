package cn.catguild.user.exception;

/**
 * @author xiyan
 * @date 2022-08-07 11:03
 */
public class AuthException extends BizException{

	public AuthException(String errorCode, String errorMessage) {
		super(errorCode, errorMessage);
	}

}
