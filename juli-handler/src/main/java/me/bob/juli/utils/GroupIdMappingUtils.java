package me.bob.juli.utils;

import me.bob.juli.domain.TaskInfo;
import me.bob.juli.enums.ChannelType;
import me.bob.juli.enums.MessageType;

import java.util.ArrayList;
import java.util.List;

/**
 * groupId 标识着每一个消费者组
 *
 * @author baobo
 * @date 2022/1/11 17:25
 */
public class GroupIdMappingUtils {

    /**
     * 获取所有的groupIds
     * (不同的渠道不同的消息类型拥有自己的groupId)
     */
    public static List<String> getAllGroupIds() {
        List<String> groupIds = new ArrayList<>();
        for (ChannelType channelType : ChannelType.values()) {
            for (MessageType messageType : MessageType.values()) {
                groupIds.add(channelType.getCode_en() + "." + messageType.getCode_en());
            }
        }
        return groupIds;
    }


    /**
     * 根据TaskInfo获取当前消息的groupId
     *
     * @param taskInfo
     * @return
     */
    public static String getGroupIdByTaskInfo(TaskInfo taskInfo) {
        String channelCodeEn = ChannelType.getEnumByCode(taskInfo.getSendChannel()).getCode_en();
        String msgCodeEn = MessageType.getEnumByCode(taskInfo.getMsgType()).getCode_en();
        return channelCodeEn + "." + msgCodeEn;
    }
}
