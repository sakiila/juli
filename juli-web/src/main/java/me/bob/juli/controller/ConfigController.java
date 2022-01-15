package me.bob.juli.controller;

import com.alibaba.fastjson.JSON;
import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author baobo
 * @date 2022/1/11 16:08
 */
@RestController
@Slf4j
public class ConfigController {

    @ApolloConfig("TEST1.juli")
    private Config config;

    @GetMapping("/config/get")
    public String configGet() {
        return JSON.toJSONString(config.getProperty("juliKey", "{}"));
    }

}
