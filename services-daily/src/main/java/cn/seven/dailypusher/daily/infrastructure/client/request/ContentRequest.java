package cn.seven.dailypusher.daily.infrastructure.client.request;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentRequest {

    /**
     * 内容名称
     */
    @NotBlank
    String contentName;

    /**
     * 项目ID
     */
    Long projectId;

    /**
     * 简报
     */
    @NotBlank
    String briefing;

    /**
     * 是否包含天气
     */
    @NotNull
    Boolean containWeather;

    /**
     * 天气城市
     */
    String cityForWeather;

    /**
     * 是否包含格言
     */
    @NotNull
    Boolean containMotto;

    /**
     * 定时类型
     */
    @NotNull
    ScheduleType scheduleType;

    /**
     * 按日期推送
     */
    @Future(message = "按日期推送，推送时间需要大于当前时间")
    Date scheduledPushDateTime;

    /**
     * 每周几循环推送。1-7分别代表周一到周日
     * 使用二进制位表示某一天需要推送。第1位为1时表示周一被选中，为0时表示周一未被选中，以此类推。
     * 例子：1111111表示周一到周日都被选中，0000001表示只有周日被选中。
     */
    @Min(value = 1, message = "使用二进制位表示某一天需要推送，最小值为1")
    @Max(value = 127, message = "使用二进制位表示某一天需要推送，最大值为127")
    Integer scheduledPushWeekDayPattern;

    /**
     * 循环推送时间。24小时制「HH:mm」
     */
    @Pattern(regexp = "^([01]\\d|2[0-3]):[0-5]\\d$", message = "时间需要满足24小时制，采用「HH:mm」格式")
    String scheduledPushDayTime;


    /**
     * 企微webhook key
     */
    List<String> enterpriseWeChatHookKeys;
}
