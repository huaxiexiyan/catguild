package cn.catguild.common.utility;

import cn.hutool.core.util.ObjectUtil;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author xiyan
 * @date 2022-04-23 23:10
 */
public class CatDateUtils {

	public static ZonedDateTime toLocalDateTime(Long epochMilli) {
		if (ObjectUtil.isEmpty(epochMilli)) {
			return null;
		}
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
	}

	public static Long toEpochMilli(ZonedDateTime zonedDateTime) {
		if (ObjectUtil.isEmpty(zonedDateTime)) {
			return null;
		}
		return zonedDateTime.toInstant().toEpochMilli();
	}

	public static Long getCurrentEpochMilli() {
		return System.currentTimeMillis();
	}

}
