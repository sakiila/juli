package me.bob.juli.receiver;


import me.bob.juli.utils.GroupIdMappingUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListenerAnnotationBeanPostProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 启动消费者
 *
 * @author baobo
 * @date 2022/1/11 17:25
 */
@Service
public class ReceiverStart {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * receiver的消费方法常量
     */
    private static final String RECEIVER_METHOD_NAME = "Receiver.consumer";

    /**
     * 获取得到所有的groupId
     */
    private static final List<String> ALL_GROUP_IDS = GroupIdMappingUtils.getAllGroupIds();

    /**
     * 下标(用于迭代groupIds位置)
     */
    private static Integer index = 0;

    /**
     * 为每个渠道不同的消息类型 创建一个Receiver对象
     */
    @PostConstruct
    public void init() {
        for (int i = 0; i < ALL_GROUP_IDS.size(); i++) {
            applicationContext.getBean(Receiver.class);
        }
    }

    /**
     * 给每个Receiver对象的consumer方法 @KafkaListener赋值相应的groupId
     */
    @Bean
    public static KafkaListenerAnnotationBeanPostProcessor.AnnotationEnhancer groupIdEnhancer() {
        return (attrs, element) -> {
            if (element instanceof Method) {
                String name = ((Method) element).getDeclaringClass().getSimpleName() + "." + ((Method) element).getName();
                if (RECEIVER_METHOD_NAME.equals(name)) {
                    attrs.put("groupId", ALL_GROUP_IDS.get(index));
                    index++;
                }
            }
            return attrs;
        };
    }
}
