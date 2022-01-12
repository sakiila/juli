package me.bob.juli.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * 模板枚举信息
 * @author baobo
 * @date 2022/1/11 17:24
 */
@Getter
@ToString
@AllArgsConstructor
public enum TemplateType {

    OPERATION(10, "运营类的模板"),
    TECHNOLOGY(20, "技术类的模板"),
    ;

    private Integer code;
    private String description;

}
