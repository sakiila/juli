package me.bob.juli.service;

import me.bob.juli.domain.SendTaskModel;
import me.bob.juli.domain.request.BatchSendRequest;
import me.bob.juli.domain.request.SendRequest;
import me.bob.juli.domain.response.SendResponse;
import me.bob.juli.pipeline.ProcessContext;
import me.bob.juli.pipeline.ProcessController;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author baobo
 * @date 2022/1/11 16:47
 */
@Service
public class SendServiceImpl implements SendService {

    @Resource
    private ProcessController processController;

    @Override
    public SendResponse send(SendRequest sendRequest) {
        BatchSendRequest batchSendRequest = BatchSendRequest.builder()
                .code(sendRequest.getCode())
                .messageTemplateId(sendRequest.getMessageTemplateId())
                .messageParamList(Collections.singletonList(sendRequest.getMessageParam()))
                .build();

        return batchSend(batchSendRequest);
    }

    @Override
    public SendResponse batchSend(BatchSendRequest batchSendRequest) {
        final SendTaskModel sendTaskModel = SendTaskModel.builder()
                .messageTemplateId(batchSendRequest.getMessageTemplateId())
                .messageParamList(batchSendRequest.getMessageParamList())
                .build();

        final ProcessContext processContext = ProcessContext.builder()
                .code(batchSendRequest.getCode())
                .processModel(sendTaskModel)
                .build();

        final ProcessContext process = processController.process(processContext);

        return new SendResponse(process.getResult().getCode(), process.getResult().getMsg());
    }

}
