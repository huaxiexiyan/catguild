package cn.catguild.guild.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * @author xiyan
 * @date 2022-03-12 17:49
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ApiResponse<T> {
	/**
	 * 如果请求 0:成功,1:失败
	 */
	private Integer success;
	/**
	 * 响应数据
	 */
	private T data;
	/**
	 * 错误类型代码
	 */
	private Integer errorCode;
	/**
	 * 向用户显示消息
	 */
	private String errorMessage;
	/**
	 * 错误显示类型： 0 无声； 1条消息。警告； 2 message.error; 4 通知； 9 页
	 */
	private Integer showType;
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

	public ApiResponse(Integer success, T data, Integer errorCode, String errorMessage, Integer showType, String traceId, String host) {
		this.success = success;
		this.data = data;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.showType = showType;
		this.traceId = traceId;
		this.host = host;
	}

	public ApiResponse(Integer errorCode) {
		this(1, null, errorCode, null, null, null, null);
	}

	public ApiResponse(Integer errorCode, String errorMessage) {
		this(1, null, errorCode, errorMessage, null, null, null);
	}

	public ApiResponse(T data) {
		this(0, data, null, null, null, null, null);
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
		objectApiResponse.setSuccess(0);
		return objectApiResponse;
	}

	/**
	 * 错误响应
	 *
	 * @param errorCode 响应码
	 * @param <T>
	 * @return
	 */
	public static <T> ApiResponse<T> fail(Integer errorCode) {
		return new ApiResponse<>(errorCode);
	}

	public static <T> ApiResponse<T> fail(String errorMessage) {
		return new ApiResponse<>(400, errorMessage);
	}

	public static <T> ApiResponse<T> fail() {
		return new ApiResponse<>(400);
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
		return this.success != null && !this.success.equals(1);
	}

}
