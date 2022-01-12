package me.bob.juli.domain.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 消息参数
 *
 * @author baobo
 * @date 2022/1/11 16:36
 */
@Data
@Accessors(chain = true)
public class MessageParam {
    /**
     * 接收者
     */
    private String receiver;

    /**
     * 消息内容中的可变部分
     */
    private Map<String, String> variables;

    /**
     * 扩展参数
     */
    private Map<String, String> extra;
}
