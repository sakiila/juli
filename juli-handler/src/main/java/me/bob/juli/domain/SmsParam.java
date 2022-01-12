package me.bob.juli.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

/**
 * 发送短信参数
 *
 * @author baobo
 * @date 2022/1/11 17:25
 */
@Data
@Builder
public class SmsParam {

    /**
     * 业务Id
     */
    private Long messageTemplateId;

    /**
     * 需要发送的手机号
     */
    private Set<String> phones;

    /**
     * 发送文案
     */
    private String content;

    /**
     * 渠道商Id
     */
    private Integer supplierId;

    /**
     * 渠道商名字
     */
    private String supplierName;
}
