package cn.catguild.common.api;

import lombok.Data;

/**
 * @author xiyan
 * @date 2022-03-12 17:49
 */
@Data
public class ApiResponse<T> {
	/**
	 * 如果请求 0:成功,1:失败
	 */
	private Boolean success;
	/**
	 * 响应数据
	 */
	private T data;
	/**
	 * 错误类型代码
	 */
	private String errorCode;
	/**
	 * 向用户显示消息
	 */
	private String errorMessage;
	/**
	 * 错误显示类型： 0 无声； 1条消息。警告； 2 message.error; 4 通知； 9 页
	 * 'success' | 'error' | 'warning';
	 */
	private String showType;
	/**
	 * 方便后端排查：唯一的请求ID
	 */
	private String traceId;
	/**
	 * 方便后端排查：当前接入服务器的主机
	 */
	private String host;

	public ApiResponse() {
	}

	public ApiResponse(Boolean success, T data, String errorCode, String errorMessage, String showType, String traceId, String host) {
		this.success = success;
		this.data = data;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.showType = "error";
		this.traceId = traceId;
		this.host = host;
	}

	public ApiResponse(String errorCode) {
		this(false, null, errorCode, null, null, null, null);
	}

	public ApiResponse(String errorCode, String errorMessage) {
		this(false, null, errorCode, errorMessage, null, null, null);
	}

	public ApiResponse(T data) {
		this(true, data, null, null, null, null, null);
	}

	/**
	 * 正常响应
	 *
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> ok(T data) {
		return new ApiResponse<>(data);
	}

	public static <T> ApiResponse<T> ok() {
		ApiResponse<T> objectApiResponse = new ApiResponse<T>();
		objectApiResponse.setSuccess(true);
		return objectApiResponse;
	}

	/**
	 * 错误响应
	 *
	 * @param errorCode 响应码
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> failCode(String errorCode) {
		return fail(errorCode, null);
	}

	public static <T> ApiResponse<T> failMessage(String errorMessage) {
		return fail("400", errorMessage);
	}

	public static <T> ApiResponse<T> fail(String errorCode,String errorMessage) {
		return new ApiResponse<>(errorCode, errorMessage);
	}

	public static <T> ApiResponse<T> fail() {
		return new ApiResponse<>("400");
	}

	public static ApiResponse<?> status(Boolean status) {
		return status != null && status ? ok() : fail();
	}

	/**
	 * 判断当前请求是否成功
	 *
	 * @return 成功：true
	 */
	public boolean isSuccess() {
		return this.success != null && this.success;
	}

}
