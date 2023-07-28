package cn.seven.dailypusher.schedule.domain.schedule.param;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduleParam {

    /**
     * 任务描述
     */
    @NotBlank
    String jobDesc;

    /**
     * 负责人
     */
    @NotBlank
    String author;

    /**
     * 报警邮件
     */
    String alarmEmail;

    /**
     * 执行器，任务参数
     */
    String executorParam;

    /**
     * 执行时间
     */
    @NotBlank
    String cron;
}
