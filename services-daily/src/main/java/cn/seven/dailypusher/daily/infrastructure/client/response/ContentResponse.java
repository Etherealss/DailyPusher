package cn.seven.dailypusher.daily.infrastructure.client.response;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentResponse {
    Long id;
    Date createTime;
    Date modifyTime;

    /**
     * 项目ID
     */
    Long projectId;

    /**
     * 简报
     */
    String briefing;

    /**
     * 是否包含天气
     */
    Boolean containWeather;

    /**
     * 是否包含格言
     */
    Boolean containMotto;

    /**
     * 定时类型
     */
    ScheduleType scheduleType;

    /**
     * 按日期发送
     */
    Date scheduledPushTime;

    /**
     * 按cron循环执行发送
     */
    String scheduledPushCron;

    /**
     * 企微webhook key
     */
    List<String> enterpriseWeChatHookKeys;
}
