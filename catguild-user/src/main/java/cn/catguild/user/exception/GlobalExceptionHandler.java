package cn.catguild.user.exception;

import cn.catguild.common.api.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @ControllerAdvice 注解效果：
 * @ExceptionHandler、@InitBinder、@ModelAttribute 注解的方法，都会作用在 被 @RequestMapping 注解的方法上。
 * @ModelAttribute：在Model上设置的值，对于所有被 @RequestMapping 注解的方法中，都可以通过 ModelMap 获取，
 *
 * @author xiyan
 * @date 2023/4/13 9:32
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BizException.class)
	public ApiResponse handleRRException(BizException e) {
		log.error(e.getMessage(), e);
		return ApiResponse.fail(e.getCode()+"", e.getMessage());
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ApiResponse handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return ApiResponse.fail("404", "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(Exception.class)
	public ApiResponse handleException(Exception e) {
		log.error(e.getMessage(), e);
		return ApiResponse.fail("500", "系统繁忙,请稍后再试");
	}


}
