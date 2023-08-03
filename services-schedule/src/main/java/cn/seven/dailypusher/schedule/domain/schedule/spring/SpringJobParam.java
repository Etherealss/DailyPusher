package cn.seven.dailypusher.schedule.domain.schedule.spring;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SpringJobParam {
    Long jobId;
    Date executionTime;
    SpringJobCallback springJobCallback;
}
