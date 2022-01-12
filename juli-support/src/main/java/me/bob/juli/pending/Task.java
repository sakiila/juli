package me.bob.juli.pending;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/**
 * Task 执行器
 * 0.丢弃消息
 * 1.通用去重功能
 * 2.发送消息
 *
 * @author baobo
 * @date 2022/1/11 22:43
 */
@Data
@Accessors(chain = true)
@Slf4j
public class Task implements Runnable {
    @Override
    public void run() {

    }
}
