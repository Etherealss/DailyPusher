package cn.seven.dailypusher.daily.domain.content.schedule.spring;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import cn.seven.dailypusher.common.base.exception.rest.ParamErrorException;
import cn.seven.dailypusher.daily.domain.content.schedule.ContentScheduleEntity;
import cn.seven.dailypusher.daily.domain.content.schedule.ContentScheduleParam;
import cn.seven.dailypusher.daily.domain.content.schedule.ScheduleStategy;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentScheduleRequest;
import cn.seven.dailypusher.daily.infrastructure.event.PushContentEvent;
import cn.seven.dailypusher.schedule.domain.schedule.spring.SpringJobCallback;
import cn.seven.dailypusher.schedule.domain.schedule.spring.SpringScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SpringScheduleStategy implements ScheduleStategy {
    private final SpringScheduleService springScheduleService;
    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public ContentScheduleParam createJob(final Long contentId, ContentScheduleRequest request) {
        Date pushDateTime = request.getScheduledPushDateTime();
        if (pushDateTime == null) {
            throw new ParamErrorException("ScheduledPushDateTime不能为空");
        }
        if (pushDateTime.before(new Date())) {
            throw new ParamErrorException("ScheduledPushDateTime需要是一个将来的时间");
        }
        ContentScheduleParam contentScheduleParam = new ContentScheduleParam();
        Long jobId = springScheduleService.createAndRunJob(pushDateTime, getSpringJobCallback(contentId));
        contentScheduleParam.setTime(request.getScheduledPushDateTime());
        contentScheduleParam.setSpringJobId(jobId);
        return contentScheduleParam;
    }

    private SpringJobCallback getSpringJobCallback(Long contentId) {
        return () -> {
            log.info("spring定时任务执行。contentId: {}", contentId);
            applicationEventPublisher.publishEvent(new PushContentEvent(contentId));
        };
    }

    @Override
    public void cacelOldJob(ContentScheduleParam contentScheduleParam) {
        springScheduleService.cancelJob(contentScheduleParam.getSpringJobId());
    }

    @Override
    public ScheduleType supportedType() {
        return ScheduleType.SPRING_DAY;
    }

    @Override
    public ContentScheduleParam createOrUpdateNewJob(ContentScheduleRequest request, ContentScheduleEntity oldContentSchedule) {
        Date pushDateTime = request.getScheduledPushDateTime();
        if (pushDateTime == null) {
            throw new ParamErrorException("ScheduledPushDateTime不能为空");
        }
        if (pushDateTime.before(new Date())) {
            throw new ParamErrorException("ScheduledPushDateTime需要是一个将来的时间");
        }
        Long springJobId = oldContentSchedule.getScheduleParam().getSpringJobId();
        if (oldContentSchedule.getScheduleType() == ScheduleType.SPRING_DAY
                && springJobId != null) {
            log.trace("更新spring定时任务");
            springScheduleService.updateJob(
                    springJobId,
                    pushDateTime,
                    getSpringJobCallback(oldContentSchedule.getContentId())
            );
            return new ContentScheduleParam()
                    .setSpringJobId(springJobId)
                    .setTime(pushDateTime);
        } else {
            log.trace("创建spring定时任务");
            return createJob(oldContentSchedule.getContentId(), request);
        }
    }
}
