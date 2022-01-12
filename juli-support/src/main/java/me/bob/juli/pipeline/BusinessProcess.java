package me.bob.juli.pipeline;

/**
 * 业务执行器
 *
 * @author baobo
 * @date 2022/1/11 16:52
 */
public interface BusinessProcess {
    /**
     * 处理逻辑
     *
     * @param context
     */
    void process(ProcessContext context);
}
