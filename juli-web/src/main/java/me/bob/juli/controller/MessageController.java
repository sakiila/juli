package me.bob.juli.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author baobo
 * @date 2022/1/11 16:08
 */
@RestController
@Slf4j
public class MessageController {
    
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    private String topic = "juli-message";
    
    @GetMapping("/message/send")
    public void messageSend(@RequestParam String message) {
        kafkaTemplate.send(topic, message);
    }
}
