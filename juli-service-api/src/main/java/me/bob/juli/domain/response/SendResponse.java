package me.bob.juli.domain.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 发送接口返回值
 *
 * @author baobo
 * @date 2022/1/11 16:36
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
public class SendResponse {
    /**
     * 响应状态
     */
    private String code;

    /**
     * 响应编码
     */
    private String msg;
}
