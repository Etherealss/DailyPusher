package cn.seven.dailypusher.daily.infrastructure.client.response;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author 王腾坤
 * @date 2023/7/29
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentScheduleResponse {
    Long contentId;

    ScheduleType scheduleType;

    Date scheduledPushTime;

    String scheduledPushCron;

    String jobId;
}
