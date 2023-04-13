package cn.catguild.user.utility;

import cn.catguild.user.exception.BizException;

/**
 * @author xiyan
 * @date 2023/2/25 13:13
 */
public class BusinessCheckUtils {

	/**
	 * 如果不是为真就抛出业务异常
	 *
	 * @param check
	 * @param msg
	 */
	public static void checkBoolean(boolean check, String msg) {
		if (check) throw new BizException(msg);
	}

}
