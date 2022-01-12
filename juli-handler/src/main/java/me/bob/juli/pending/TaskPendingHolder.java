package me.bob.juli.pending;

import me.bob.juli.config.ThreadPoolConfig;
import me.bob.juli.utils.GroupIdMappingUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;


/**
 * 存储 每种消息类型 与 TaskPending 的关系
 *
 * @author baobo
 * @date 2022/1/11 17:25
 */
@Component
public class TaskPendingHolder {
    /**
     * 线程池的参数
     */
    private Integer coreSize = 3;
    private Integer maxSize = 3;
    private Integer queueSize = 100;
    private Map<String, ExecutorService> taskPendingHolder = new HashMap<>(32);

    /**
     * 获取得到所有的groupId
     */
    private static final List<String> ALL_GROUP_IDS = GroupIdMappingUtils.getAllGroupIds();

    /**
     * 给每个渠道，每种消息类型初始化一个线程池
     */
    @PostConstruct
    public void init() {
        for (String groupId : ALL_GROUP_IDS) {
            taskPendingHolder.put(groupId, ThreadPoolConfig.getThreadPool(coreSize, maxSize, queueSize));
        }
    }

    /**
     * 得到对应的线程池
     *
     * @param groupId
     * @return
     */
    public ExecutorService route(String groupId) {
        return taskPendingHolder.get(groupId);
    }


}
