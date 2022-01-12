package me.bob.juli.utils;

import cn.hutool.core.collection.CollUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author baobo
 * @date 2022/1/12 23:11
 */
@Component
@Slf4j
public class RedisUtils {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public Map<String, String> mGet(List<String> keys) {
        Map<String, String> result = new HashMap<>(keys.size());

        final List<String> values = stringRedisTemplate.opsForValue().multiGet(keys);
        if (CollUtil.isNotEmpty(values)) {
            result = values.stream().collect(Collectors.toMap(key -> key, key -> values.get(keys.indexOf(key))));
        }

        return result;
    }

    public void pipelineSetEX(Map<String, String> keyValues, Long seconds) {
        stringRedisTemplate.executePipelined((RedisCallback<String>) connection -> {
            keyValues.forEach((key, value) -> connection.setEx(key.getBytes(), seconds, value.getBytes()));
            return null;
        });
    }
}
