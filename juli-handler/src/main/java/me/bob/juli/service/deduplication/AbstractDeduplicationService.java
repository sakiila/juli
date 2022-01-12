package me.bob.juli.service.deduplication;

import lombok.extern.slf4j.Slf4j;
import me.bob.juli.domain.DeduplicationParam;
import me.bob.juli.domain.TaskInfo;
import me.bob.juli.utils.RedisUtils;

import javax.annotation.Resource;

/**
 * @author baobo
 * @date 2022/1/12 23:10
 */
@Slf4j
public abstract class AbstractDeduplicationService {

    @Resource
    private RedisUtils redisUtils;

    public void deduplication(DeduplicationParam param) {
        
    }

    /**
     * 构建去重的Key
     *
     * @param taskInfo
     * @param receiver
     * @return
     */
    protected abstract String deduplicationSingleKey(TaskInfo taskInfo, String receiver);
}
