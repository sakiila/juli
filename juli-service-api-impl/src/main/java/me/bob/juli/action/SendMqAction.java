package me.bob.juli.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import lombok.extern.slf4j.Slf4j;
import me.bob.juli.domain.SendTaskModel;
import me.bob.juli.pipeline.BusinessProcess;
import me.bob.juli.pipeline.ProcessContext;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.Resource;

/**
 * @author baobo
 * @date 2022/1/11 17:40
 */
@Slf4j
public class SendMqAction implements BusinessProcess {
    
    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    private String topic = "juli-message";

    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();

        kafkaTemplate.send(topic,
                JSON.toJSONString(sendTaskModel.getTaskInfo(), SerializerFeature.WriteClassName));
    }
}
