package me.bob.juli.receiver;


import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import me.bob.juli.domain.TaskInfo;
import me.bob.juli.pending.Task;
import me.bob.juli.pending.TaskPendingHolder;
import me.bob.juli.utils.GroupIdMappingUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * 消费MQ的消息
 *
 * @author baobo
 * @date 2022/1/11 17:25
 */
@Slf4j
public class Receiver {

    private static final String LOG_BIZ_TYPE = "Receiver#consumer";

    @Resource
    private ApplicationContext applicationContext;

    @Resource
    private TaskPendingHolder taskPendingHolder;

    @KafkaListener(topics = "juli-message")
    public void consumer(ConsumerRecord<?, String> consumerRecord, @Header(KafkaHeaders.GROUP_ID) String topicGroupId) {
        Optional<String> kafkaMessage = Optional.ofNullable(consumerRecord.value());
        if (kafkaMessage.isPresent()) {

            List<TaskInfo> TaskInfoLists = JSON.parseArray(kafkaMessage.get(), TaskInfo.class);
            String messageGroupId = GroupIdMappingUtils.getGroupIdByTaskInfo(TaskInfoLists.get(0));

            /**
             * 每个消费者组 只消费 他们自身关心的消息
             */
            if (topicGroupId.equals(messageGroupId)) {
                for (TaskInfo taskInfo : TaskInfoLists) {
                    Task task = applicationContext.getBean(Task.class).setTaskInfo(taskInfo);
                    taskPendingHolder.route(topicGroupId).execute(task);
                }
            }
        }
    }
}
