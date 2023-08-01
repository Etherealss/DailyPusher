package cn.seven.dailypusher.daily.domain.content.schedule;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentScheduleRequest;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
public interface ScheduleStategy {

    ScheduleType supportedType();

    ContentScheduleParam createJob(Long contentId, ContentScheduleRequest request);

    void cacelOldJob(ContentScheduleParam contentScheduleParam);

    ContentScheduleParam createOrUpdateNewJob(ContentScheduleRequest request, ContentScheduleEntity oldContentSchedule);
}
