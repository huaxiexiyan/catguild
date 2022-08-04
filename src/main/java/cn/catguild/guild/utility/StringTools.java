package cn.catguild.guild.utility;

/**
 * @author xiyan
 * @date 2022-07-24 17:27
 */
public class StringTools extends org.springframework.util.StringUtils {

	public static boolean isNotBlank(String name) {
		return !isBlank(name);
	}

	public static boolean isBlank(String name) {
		return hasText(name);
	}

}
