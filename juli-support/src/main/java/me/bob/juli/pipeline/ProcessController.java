package me.bob.juli.pipeline;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * @author baobo
 * @date 2022/1/11 16:51
 */
@Data
@Slf4j
public class ProcessController {
    /**
     * 模板映射
     */
    private Map<String, ProcessTemplate> templateConfig;

    public ProcessContext process(ProcessContext context) {
        /**
         * 前置检查
         */
        if (!preCheck(context)) {
            return context;
        }

        /**
         * 遍历流程节点
         */
        final List<BusinessProcess> processList = templateConfig.get(context.getCode()).getProcessList();
        for (BusinessProcess businessProcess : processList) {
            businessProcess.process(context);
            if (context.getNeedBreak()) {
                break;
            }
        }
        return context;
    }

    private Boolean preCheck(ProcessContext context) {
        return true;
    }

}
