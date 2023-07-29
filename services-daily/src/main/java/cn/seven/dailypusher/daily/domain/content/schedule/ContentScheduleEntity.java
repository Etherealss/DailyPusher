package cn.seven.dailypusher.daily.domain.content.schedule;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import cn.seven.dailypusher.common.base.pojo.entity.IdentifiedEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author 王腾坤
 * @date 2023/7/29
 */
@TableName("content_schedule")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ContentScheduleEntity extends IdentifiedEntity {
    @TableField("content_id")
    Long contentId;

    @TableField("schedule_type")
    ScheduleType scheduleType;

    @TableField("scheduled_push_time")
    Date scheduledPushTime;

    @TableField("scheduled_push_cron")
    String scheduledPushCron;

    @TableField("job_id")
    String jobId;

    // TODO 定时任务运行状态
}
