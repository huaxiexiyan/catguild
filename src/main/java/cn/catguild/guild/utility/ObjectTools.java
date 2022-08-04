package cn.catguild.guild.utility;

import javax.annotation.Nullable;

/**
 * @author xiyan
 * @date 2022-04-09 11:16
 */
public class ObjectTools extends org.springframework.util.ObjectUtils {

	public static boolean isNotEmpty(@Nullable Object obj) {
		return !isEmpty(obj);
	}

	public static boolean notEquals(@Nullable Object o1, @Nullable Object o2) {
		return !nullSafeEquals(o1, o2);
	}
}
