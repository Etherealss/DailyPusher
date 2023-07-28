package cn.seven.dailypusher.common.cron.remote.param;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author 王腾坤
 * @date 2023/7/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class XxlJobTaskIdParam {
    Integer id;
}
