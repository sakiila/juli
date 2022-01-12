package me.bob.juli.domain.request;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 发送接口的参数
 *
 * @author baobo
 * @date 2022/1/11 16:35
 */
@Data
@Accessors(chain = true)
public class SendRequest {
    /**
     * 执行业务类型
     */
    private String code;

    /**
     * 消息模板Id
     */
    private Long messageTemplateId;

    /**
     * 消息相关的参数
     */
    private MessageParam messageParam;
}
