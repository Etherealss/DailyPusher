package cn.seven.dailypusher.daily.infrastructure.client.request;

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
public class ContentScheduleRequest {
    /**
     * @see ContentRequest#getScheduleType()
     */
    ScheduleType scheduleType;

    /**
     * @see ContentRequest#getScheduledPushDateTime()
     */
    Date scheduledPushDateTime;

    /**
     * @see ContentRequest#getScheduledPushWeekDayPattern()
     */
    Integer scheduledPushWeekDayPattern;

    /**
     * @see ContentRequest#getScheduledPushDayTime()
     */
    String scheduledPushDayTime;


    /**
     * @see ContentRequest#getEnterpriseWeChatHookKeys()
     */
    List<String> enterpriseWeChatHookKeys;

    /**
     * 任务描述
     */
    String jobDesc;
}
