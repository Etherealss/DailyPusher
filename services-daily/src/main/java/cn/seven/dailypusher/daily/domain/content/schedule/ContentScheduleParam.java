package cn.seven.dailypusher.daily.domain.content.schedule;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author 王腾坤
 * @date 2023/7/31
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentScheduleParam {
    String cron;
    Integer xxlJobId;
    String time;
}
