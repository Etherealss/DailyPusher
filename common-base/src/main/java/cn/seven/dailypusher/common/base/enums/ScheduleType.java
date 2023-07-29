package cn.seven.dailypusher.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 王腾坤
 * @date 2023/7/29
 */
@Getter
@AllArgsConstructor
public enum ScheduleType implements BaseEnum {
    SPRING_DAY(0, "按日期执行"),
    XXL_JOB_CRON(1, "按cron表达式执行"),
    ;

    private final int code;
    private final String name;

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getCode() {
        return 0;
    }
}
