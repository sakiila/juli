package me.bob.juli.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 执行业务类型
 *
 * @author baobo
 * @date 2022/1/11 16:38
 */
@Getter
@ToString
@AllArgsConstructor
public enum BusinessCode {

    COMMON_SEND("send", "普通发送"),
    RECALL("recall", "撤回消息"),
    ;

    /**
     * 关联责任链的模板
     */
    private String code;

    /**
     * 类型说明
     */
    private String description;
}
