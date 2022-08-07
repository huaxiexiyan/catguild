package cn.catguild.user.exception;

import cn.catguild.common.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xiyan
 * @date 2022-08-07 14:13
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {



	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BizException.class)
	public ApiResponse<AuthException> handleAuthException(BizException e) {
		log.error(e.getMessage(), e);
		return ApiResponse.fail(e.getErrorCode(), e.getErrorMessage());
	}


	@ExceptionHandler(Exception.class)
	public ApiResponse<AuthException> handleException(Exception e) {
		log.error(e.getMessage(), e);
		return ApiResponse.fail("500", "系统繁忙,请稍后再试");
	}


}
