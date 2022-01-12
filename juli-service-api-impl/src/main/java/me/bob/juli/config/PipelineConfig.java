package me.bob.juli.config;

import me.bob.juli.action.AfterParamCheckAction;
import me.bob.juli.action.AssembleAction;
import me.bob.juli.action.PreParamCheckAction;
import me.bob.juli.action.SendMqAction;
import me.bob.juli.enums.BusinessCode;
import me.bob.juli.pipeline.BusinessProcess;
import me.bob.juli.pipeline.ProcessController;
import me.bob.juli.pipeline.ProcessTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author baobo
 * @date 2022/1/11 17:34
 */
@Configuration
public class PipelineConfig {

    @Bean
    public ProcessController processController() {
        Map<String, ProcessTemplate> templateConfig = new HashMap<>(4);
        templateConfig.put(BusinessCode.COMMON_SEND.getCode(), commonSendTemplate());

        ProcessController processController = new ProcessController();
        processController.setTemplateConfig(templateConfig);
        return processController;
    }

    @Bean("commonSendTemplate")
    public ProcessTemplate commonSendTemplate() {
        List<BusinessProcess> processList = new ArrayList<>();

        processList.add(preParamCheckAction());
        processList.add(assembleAction());
        processList.add(afterParamCheckAction());
        processList.add(sendMqAction());

        final ProcessTemplate processTemplate = new ProcessTemplate();
        processTemplate.setProcessList(processList);
        return processTemplate;
    }


    /**
     * 组装参数Action
     *
     * @return
     */
    @Bean
    public AssembleAction assembleAction() {
        return new AssembleAction();
    }

    /**
     * 前置参数校验Action
     *
     * @return
     */
    @Bean
    public PreParamCheckAction preParamCheckAction() {
        return new PreParamCheckAction();
    }

    /**
     * 后置参数校验Action
     *
     * @return
     */
    @Bean
    public AfterParamCheckAction afterParamCheckAction() {
        return new AfterParamCheckAction();
    }

    /**
     * 发送消息至MQ的Action
     *
     * @return
     */
    @Bean
    public SendMqAction sendMqAction() {
        return new SendMqAction();
    }

}
