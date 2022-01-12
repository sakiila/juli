package me.bob.juli.handler;


import me.bob.juli.domain.TaskInfo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * 发送各个渠道的handler
 *
 * @author baobo
 * @date 2022/1/11 17:25
 */
public abstract class Handler {

    /**
     * 标识渠道的Code
     * 子类初始化的时候指定
     */
    protected Integer channelCode;

    @Resource
    private HandlerHolder handlerHolder;

    /**
     * 初始化渠道与Handler的映射关系
     */
    @PostConstruct
    private void init() {
        handlerHolder.putHandler(channelCode, this);
    }

    public void doHandler(TaskInfo taskInfo) {
        if (handler(taskInfo)) {
            return;
        }
    }

    /**
     * 统一处理的handler接口
     *
     * @param taskInfo
     * @return
     */
    public abstract boolean handler(TaskInfo taskInfo);

}
