package me.bob.juli.dao;

import me.bob.juli.domain.SmsRecord;
import org.springframework.data.repository.CrudRepository;

/**
 * @author baobo
 * @date 2022/1/11 17:52
 */
public interface SmsRecordDao extends CrudRepository<SmsRecord, Long> {
}
