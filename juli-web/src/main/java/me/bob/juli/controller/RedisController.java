package me.bob.juli.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author baobo
 * @date 2022/1/11 16:08
 */
@RestController
@Slf4j
public class RedisController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/redis/get/{key}")
    public String redisGet(@PathVariable(name = "key") String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/redis/set/{key}")
    public String redisSet(@PathVariable(name = "key") String key) {
         redisTemplate.opsForValue().set(key, "hello " + key);
         return "success";
    }
}
