package me.bob.juli.action;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import me.bob.juli.dao.MessageTemplateDao;
import me.bob.juli.domain.MessageTemplate;
import me.bob.juli.domain.SendTaskModel;
import me.bob.juli.domain.TaskInfo;
import me.bob.juli.domain.request.MessageParam;
import me.bob.juli.dto.ContentModel;
import me.bob.juli.enums.ChannelType;
import me.bob.juli.pipeline.BusinessProcess;
import me.bob.juli.pipeline.ProcessContext;
import me.bob.juli.utils.ContentHolderUtil;
import me.bob.juli.utils.TaskInfoUtils;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;

/**
 * @author baobo
 * @date 2022/1/11 17:40
 */
@Slf4j
public class AssembleAction implements BusinessProcess {

    @Resource
    private MessageTemplateDao messageTemplateDao;

    @Override
    public void process(ProcessContext context) {
        SendTaskModel sendTaskModel = (SendTaskModel) context.getProcessModel();
        final Long messageTemplateId = sendTaskModel.getMessageTemplateId();

        final Optional<MessageTemplate> messageTemplate = messageTemplateDao.findById(messageTemplateId);

        List<TaskInfo> taskInfos = assembleTaskInfo(sendTaskModel, messageTemplate.get());
        sendTaskModel.setTaskInfo(taskInfos);
    }

    /**
     * 组装 TaskInfo 任务消息
     */
    private List<TaskInfo> assembleTaskInfo(SendTaskModel sendTaskModel, MessageTemplate messageTemplate) {
        final List<TaskInfo> taskInfoList = new ArrayList<>();

        final List<MessageParam> messageParamList = sendTaskModel.getMessageParamList();
        for (MessageParam messageParam : messageParamList) {
            TaskInfo taskInfo = TaskInfo.builder()
                    .messageTemplateId(messageTemplate.getId())
                    .businessId(TaskInfoUtils.generateBusinessId(messageTemplate.getId(), messageTemplate.getTemplateType()))
                    .receiver(new HashSet<>(Arrays.asList(messageParam.getReceiver().split(String.valueOf(StrUtil.C_COMMA)))))
                    .idType(messageTemplate.getIdType())
                    .sendChannel(messageTemplate.getSendChannel())
                    .templateType(messageTemplate.getTemplateType())
                    .msgType(messageTemplate.getMsgType())
                    .sendAccount(messageTemplate.getSendAccount())
                    .contentModel(getContentModelValue(messageTemplate, messageParam))
                    .deduplicationTime(messageTemplate.getDeduplicationTime())
                    .isNightShield(messageTemplate.getIsNightShield()).build();

            taskInfoList.add(taskInfo);
        }

        return taskInfoList;
    }

    /**
     * 获取 contentModel，替换模板msgContent中占位符信息
     */
    private static ContentModel getContentModelValue(MessageTemplate messageTemplate, MessageParam messageParam) {
        // 得到真正的ContentModel 类型
        Integer sendChannel = messageTemplate.getSendChannel();
        Class contentModelClass = ChannelType.getChanelModelClassByCode(sendChannel);

        // 得到模板的 msgContent 和 入参
        Map<String, String> variables = messageParam.getVariables();
        JSONObject jsonObject = JSON.parseObject(messageTemplate.getMsgContent());

        // 通过反射 组装出 contentModel
        Field[] fields = ReflectUtil.getFields(contentModelClass);
        ContentModel contentModel = (ContentModel) ReflectUtil.newInstance(contentModelClass);

        for (Field field : fields) {
            final String originValue = jsonObject.getString(field.getName());

            if (StrUtil.isNotBlank(originValue)) {
                String resultValue = ContentHolderUtil.replacePlaceHolder(originValue, variables);
                ReflectUtil.setFieldValue(contentModel, field, resultValue);
            }
        }

        // 如果 url 字段存在，则在url拼接对应的埋点参数
        String url = (String) ReflectUtil.getFieldValue(contentModel, "url");
        if (StrUtil.isNotBlank(url)) {
            String resultUrl = TaskInfoUtils.generateUrl(url, messageTemplate.getId(), messageTemplate.getTemplateType());
            ReflectUtil.setFieldValue(contentModel, "url", resultUrl);
        }
        return contentModel;
    }
}
