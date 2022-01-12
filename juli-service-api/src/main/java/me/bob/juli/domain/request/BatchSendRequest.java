package me.bob.juli.domain.request;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 批量发送接口的参数
 *
 * @author baobo
 * @date 2022/1/11 16:37
 */
@Data
@Builder
@Accessors(chain = true)
public class BatchSendRequest {
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
    private List<MessageParam> messageParamList;

}
