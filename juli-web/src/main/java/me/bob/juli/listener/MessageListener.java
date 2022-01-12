package me.bob.juli.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author baobo
 * @date 2022/1/11 16:11
 */
@Component
@Slf4j
public class MessageListener {

    @KafkaListener(topics = "juli-message", groupId = "test")
    public void receive(String message) {
        log.info("message: {}", message);
    }
}
