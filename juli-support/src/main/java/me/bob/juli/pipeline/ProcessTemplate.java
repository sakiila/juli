package me.bob.juli.pipeline;

import lombok.Data;

import java.util.List;

/**
 * 业务执行模板
 *
 * @author baobo
 * @date 2022/1/11 16:58
 */
@Data
public class ProcessTemplate {

    private List<BusinessProcess> processList;
}
