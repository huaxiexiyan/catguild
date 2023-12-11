package cn.catguild.common.utility;

import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * @author xiyan
 * @date 2023/10/23 13:42
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {
    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return !isEmpty(map);
    }

}
