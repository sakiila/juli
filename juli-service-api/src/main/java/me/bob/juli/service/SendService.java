package me.bob.juli.service;

import me.bob.juli.domain.request.BatchSendRequest;
import me.bob.juli.domain.request.SendRequest;
import me.bob.juli.domain.response.SendResponse;

/**
 * 发送接口
 *
 * @author baobo
 * @date 2022/1/11 16:34
 */
public interface SendService {
    /**
     * 单文案发送接口
     *
     * @param sendRequest
     * @return
     */
    SendResponse send(SendRequest sendRequest);

    /**
     * 多文案发送接口
     *
     * @param batchSendRequest
     * @return
     */
    SendResponse batchSend(BatchSendRequest batchSendRequest);

}
