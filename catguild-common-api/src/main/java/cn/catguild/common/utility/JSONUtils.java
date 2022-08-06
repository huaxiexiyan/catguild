package cn.catguild.common.utility;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiyan
 * @date 2022-04-25 15:29
 */
public class JSONUtils {

	private static final ObjectMapper objectMapper = new ObjectMapper();

	public static <T> List<T> parseArray(String content, Class<T> valueTypeRef) {
		try {
			List<Map<String, Object>> list = objectMapper.readValue(content, new TypeReference<>() {
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

	private static <T> T toPojo(Map<String, Object> map, Class<T> valueTypeRef) {
		return objectMapper.convertValue(map, valueTypeRef);
	}
}
