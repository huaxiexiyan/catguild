package cn.catguild.business.erp.infrastructure.adapter.cache;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.Collection;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/9/26 11:20
 */
public interface CacheClient {

    /**
     * 追加
     *
     * @param key 缓存键
     * @param list 保存对象
     */
    void save(String key, List<?> list);

    /**
     * 追加
     *
     * @param key 缓存键
     * @param list 保存对象
     */
    void add(String key, List<?> list);

    /**
     * 保存
     *
     * @param key 缓存键
     * @param obj 保存对象
     */
    void save(String key, Object obj);

    RedisTemplate<String, Object>  getRedisTemplate();

    List<String> getKeys(String pattern);

    String getString(String key);

    <T> T getObject(String key,Class<T> className);

    void remove(String key);

    void remove(Collection<String> keys);

}
