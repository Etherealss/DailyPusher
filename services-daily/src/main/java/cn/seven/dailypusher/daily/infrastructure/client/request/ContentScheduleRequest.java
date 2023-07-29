package cn.seven.dailypusher.daily.infrastructure.client.request;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentScheduleRequest {
    /**
     * 定时类型
     */
    @NotNull
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
     * 任务描述
     */
    @NotBlank
    String jobDesc;
}
