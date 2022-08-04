package cn.catguild.guild.utility;

import javax.annotation.Nullable;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author xiyan
 * @date 2022-04-23 23:10
 */
public class CatDateUtils {

	@Nullable
	public static ZonedDateTime toLocalDateTime(Long epochMilli) {
		if (ObjectTools.isEmpty(epochMilli)) {
			return null;
		}
		return ZonedDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), ZoneId.systemDefault());
	}

	@Nullable
	public static Long toEpochMilli(ZonedDateTime zonedDateTime) {
		if (ObjectTools.isEmpty(zonedDateTime)) {
			return null;
		}
		return zonedDateTime.toInstant().toEpochMilli();
	}

	public static Long getCurrentEpochMilli() {
		return System.currentTimeMillis();
	}

}
