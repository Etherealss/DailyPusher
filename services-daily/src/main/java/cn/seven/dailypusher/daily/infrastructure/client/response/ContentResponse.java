package cn.seven.dailypusher.daily.infrastructure.client.response;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentRequest;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

/**
 * @see ContentRequest 字段解释
 * @author 王腾坤
 * @date 2023/7/28
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentResponse {
    Long id;
    Date createTime;
    Date modifyTime;
    String contentName;
    Long projectId;
    String briefing;
    Boolean containWeather;
    String cityForWeather;
    Boolean containMotto;
    ScheduleType scheduleType;
    Date scheduledPushDateTime;
    Integer scheduledPushWeekDayPattern;
    String scheduledPushDayTime;
    List<String> enterpriseWeChatHookKeys;
}