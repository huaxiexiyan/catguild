package cn.catguild.auth.infrastructure.adapter.cache.impl;

import cn.catguild.auth.infrastructure.adapter.cache.CacheClient;
import cn.catguild.common.utility.JSONUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiyan
 * @date 2023/9/26 11:28
 */
@Slf4j
@Component
@AllArgsConstructor
public class RedisCacheClient implements CacheClient {

    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public void add(String key, List<?> list) {
        list.forEach(item -> redisTemplate.opsForList().leftPush(key, item));
    }

    @Override
    public void save(String key, List<?> list) {
        redisTemplate.delete(key);
        add(key, list);
    }

    @Override
    public void save(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj);
    }

    @Override
    public RedisTemplate<String, Object> getRedisTemplate() {
        return redisTemplate;
    }

    @Override
    public List<String> getKeys(String pattern) {
        List<String> keys = new ArrayList<>();
        try (Cursor<String> scan = redisTemplate.scan(ScanOptions.scanOptions().match(pattern).build())) {
            scan.stream().forEach(keys::add);
        }
        return keys;
    }

    @Override
    public String getString(String key) {
        Object obj = redisTemplate.opsForValue().get(key);
        return obj == null ? "{}" : JSONUtils.toJsonStr(obj);
    }

    @Override
    public <T> T getObject(String key, Class<T> className) {
        Object obj = redisTemplate.opsForValue().get(key);
        return obj == null ? null : JSONUtils.toPojo(obj, className);
    }


}
