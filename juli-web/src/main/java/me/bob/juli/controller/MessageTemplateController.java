package me.bob.juli.controller;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import me.bob.juli.dao.MessageTemplateDao;
import me.bob.juli.domain.MessageTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @author baobo
 * @date 2022/1/11 16:08
 */
@RestController
public class MessageTemplateController {

    @Resource
    private MessageTemplateDao messageTemplateDao;

    @GetMapping("/insert")
    public String insert() {

        MessageTemplate messageTemplate = MessageTemplate.builder()
                .name("test邮件")
                .auditStatus(10)
                .flowId("yyyy")
                .msgStatus(10)
                .idType(50)
                .sendChannel(40)
                .templateType(20)
                .msgType(10)
                .expectPushTime("0")
                .msgContent("{\"content\":\"{$contentValue}\",\"title\":\"{$title}\"}")
                .sendAccount(66)
                .creator("yyyyc")
                .updator("yyyyu")
                .team("yyyt")
                .proposer("yyyy22")
                .auditor("yyyyyyz")
                .isDeleted(0)
                .created(Math.toIntExact(DateUtil.currentSeconds()))
                .updated(Math.toIntExact(DateUtil.currentSeconds()))
                .deduplicationTime(1)
                .isNightShield(0)
                .build();

        MessageTemplate info = messageTemplateDao.save(messageTemplate);

        return JSON.toJSONString(info);

    }

    @GetMapping("/query")
    public String query() {
        Iterable<MessageTemplate> all = messageTemplateDao.findAll();
        for (MessageTemplate messageTemplate : all) {
            return JSON.toJSONString(messageTemplate);
        }
        return null;
    }
}
