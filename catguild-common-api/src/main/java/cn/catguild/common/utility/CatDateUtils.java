package cn.catguild.common.utility;

import org.springframework.util.ObjectUtils;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author xiyan
 * @date 2022-04-23 23:10
 */
public class CatDateUtils {

	public static ZonedDateTime toLocalDateTime(Long epochMilli) {
		if (ObjectUtils.isEmpty(epochMilli)) {
			return null;
		}
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
	}

	public static Long toEpochMilli(ZonedDateTime zonedDateTime) {
		if (ObjectUtils.isEmpty(zonedDateTime)) {
			return null;
		}
		return zonedDateTime.toInstant().toEpochMilli();
	}

	public static Long getCurrentEpochMilli() {
		return System.currentTimeMillis();
	}

}
