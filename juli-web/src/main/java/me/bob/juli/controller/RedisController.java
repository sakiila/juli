package me.bob.juli.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
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
    
    @RequestMapping("/redis/get")
    public void redisGet() {
        log.info(redisTemplate.opsForValue().get("1"));
    }
}
