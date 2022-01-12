package me.bob.juli.config;


import me.bob.juli.pending.Task;
import me.bob.juli.receiver.Receiver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Handler模块的配置信息
 *
 * @author baobo
 * @date 2022/1/11 17:25
 */
@Configuration
public class PrototypeBeanConfig {

    /**
     * 定义多例的Receiver
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Receiver receiver() {
        return new Receiver();
    }

    /**
     * 定义多例的Task
     */
    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public Task task() {
        return new Task();
    }

}
