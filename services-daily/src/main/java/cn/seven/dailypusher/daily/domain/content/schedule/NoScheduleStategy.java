package cn.seven.dailypusher.daily.domain.content.schedule;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentScheduleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class NoScheduleStategy implements ScheduleStategy {
    @Override
    public ContentScheduleParam createJob(Long contentId, ContentScheduleRequest request) {
        return new ContentScheduleParam();
    }

    @Override
    public void cacelOldJob(ContentScheduleParam contentScheduleParam) {
        throw new UnsupportedOperationException("ScheduleType.NO_SCHEDULE 不支持取消定时任务。contentScheduleParam:" + contentScheduleParam.toString());
    }

    @Override
    public ScheduleType supportedType() {
        return ScheduleType.NO_SCHEDULE;
    }

    @Override
    public ContentScheduleParam createOrUpdateNewJob(ContentScheduleRequest request, ContentScheduleEntity oldContentSchedule) {
        return new ContentScheduleParam();
    }
}
