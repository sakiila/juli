package me.bob.juli.domain;


import lombok.Builder;
import lombok.Data;
import me.bob.juli.enums.AnchorState;

/**
 * 去重服务所需要的参数
 *
 * @author baobo
 * @date 2022/1/11 17:25
 */
@Builder
@Data
public class DeduplicationParam {

    /**
     * TaskIno信息
     */
    private TaskInfo taskInfo;

    /**
     * 去重时间
     * 单位：秒
     */
    private Long deduplicationTime;

    /**
     * 需达到的次数去重
     */
    private Integer countNum;

    /**
     * 标识属于哪种去重
     */
    private AnchorState anchorState;

}
