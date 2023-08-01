package cn.seven.dailypusher.daily.infrastructure.client.request;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
//    @NotNull
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

    // TODO 天气城市

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
     * 按日期发送
     */
    Date scheduledPushTime;

    /**
     * 按cron循环执行发送
     */
    String scheduledPushCron;

    /**
     * 企微webhook key
     */
    List<String> enterpriseWeChatHookKeys;
}
