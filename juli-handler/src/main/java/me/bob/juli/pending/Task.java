package me.bob.juli.pending;


import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import me.bob.juli.domain.TaskInfo;
import me.bob.juli.handler.HandlerHolder;
import me.bob.juli.service.deduplication.DeduplicationRuleService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;

/**
 * Task 执行器
 * 0.丢弃消息
 * 1.通用去重功能
 * 2.发送消息
 *
 * @author baobo
 * @date 2022/1/11 17:25
 */
@Data
@Accessors(chain = true)
@Slf4j
public class Task implements Runnable {

    @Autowired
    private HandlerHolder handlerHolder;

    @Resource
    private DeduplicationRuleService deduplicationRuleService;

//    @Resource
//    private DiscardMessageService discardMessageService;

    private TaskInfo taskInfo;


    @Override
    public void run() {

        // 0. 丢弃消息
//        if (discardMessageService.isDiscard(taskInfo)) {
//            return;
//        }

        // 1.平台通用去重
        deduplicationRuleService.duplication(taskInfo);

        // 2. 真正发送消息
        if (CollUtil.isNotEmpty(taskInfo.getReceiver())) {
            handlerHolder.route(taskInfo.getSendChannel())
                    .doHandler(taskInfo);
        }

    }
}
