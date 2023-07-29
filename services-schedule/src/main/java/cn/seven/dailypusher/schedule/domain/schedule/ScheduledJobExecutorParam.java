package cn.seven.dailypusher.schedule.domain.schedule;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ScheduledJobExecutorParam {
    Long contentId;
}
