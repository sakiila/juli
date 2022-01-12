package me.bob.juli.dao;

import me.bob.juli.domain.MessageTemplate;
import org.springframework.data.repository.CrudRepository;

/**
 * @author baobo
 * @date 2022/1/11 17:51
 */
public interface MessageTemplateDao extends CrudRepository<MessageTemplate, Long> {
}
