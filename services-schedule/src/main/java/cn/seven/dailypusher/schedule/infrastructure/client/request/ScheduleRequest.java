package cn.seven.dailypusher.schedule.infrastructure.client.request;

import cn.seven.dailypusher.schedule.domain.schedule.ScheduledJobExecutorParam;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author 王腾坤
 * @date 2023/7/29
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleRequest {

    /**
     * 任务描述
     */
    String jobDesc;

    /**
     * 负责人
     */
    String author;

    /**
     * 执行时间
     */
    String cron;

    String executorHandlerName;

    /**
     * 执行时附带的参数
     */
    ScheduledJobExecutorParam executorParam;

    /**
     * 是否立即执行
     */
    Boolean startRightNow;
}
