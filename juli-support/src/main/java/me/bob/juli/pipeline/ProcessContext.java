package me.bob.juli.pipeline;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import me.bob.juli.result.BasicResult;

/**
 * 责任链上下文
 *
 * @author baobo
 * @date 2022/1/11 16:53
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class ProcessContext {
    /**
     * 责任链标识
     */
    private String code;

    /**
     * 存储责任链上下文数据的模型
     */
    private ProcessModel processModel;

    /**
     * 责任链中断的标识
     */
    private Boolean needBreak;

    /**
     * 结果
     */
    private BasicResult<Void> result;
    
}
