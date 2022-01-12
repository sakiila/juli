package me.bob.juli.utils;

import cn.hutool.core.date.DateUtil;

import java.util.Date;

/**
 * @author baobo
 * @date 2022/1/11 19:25
 */
public class TaskInfoUtils {

    /**
     * 生成BusinessId
     * 模板类型+模板ID+当天日期
     * (固定16位)
     */
    public static Long generateBusinessId(Long templateId, Integer templateType) {
        final Integer today = Integer.valueOf(DateUtil.format(new Date(), "yyyyMMdd"));
        return Long.valueOf(String.format("%d%s", templateType * 1000000 + templateId, today));
    }

    /**
     * 对url添加平台参数（用于追踪数据)
     */
    public static String generateUrl(String url, Long templateId, Integer templateType) {
        url = url.trim();
        Long businessId = generateBusinessId(templateId, templateType);
        if (url.indexOf('?') == -1) {
            return url + "?track_code_bid=" + businessId;
        } else {
            return url + "&track_code_bid=" + businessId;
        }
    }
}
