package cn.seven.dailypusher.common.cron.remote.param;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author 王腾坤
 * @date 2023/7/27
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class XxlJobTaskIdParam {
    String id;
}
