package cn.catguild.common.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiyan
 * @date 2022-04-25 15:29
 */
public class JSONUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	public static <T> List<T> parseArray(String jsonStr, Class<T> valueTypeRef) {
		try {
			List<Map<String, Object>> list = objectMapper.readValue(jsonStr, new TypeReference<>() {
			});
			List<T> result = new ArrayList<>();

			for (Map<String, Object> map : list) {
				result.add(toPojo(map, valueTypeRef));
			}
			return result;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T parse(String jsonStr,Class<T> valueTypeRef) {
		try {
			Map<String, Object> objMap = objectMapper.readValue(jsonStr, new TypeReference<>() {
			});
			return toPojo(objMap, valueTypeRef);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <T> T toPojo(Map<String, Object> map, Class<T> valueTypeRef) {
		return objectMapper.convertValue(map, valueTypeRef);
	}

	public static <T> T toPojo(Object obj, Class<T> valueTypeRef) {
		return objectMapper.convertValue(obj, valueTypeRef);
	}

	// 序列化对象为JSON字符串
	public static String toJsonStr(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return null;
		}
	}



}
