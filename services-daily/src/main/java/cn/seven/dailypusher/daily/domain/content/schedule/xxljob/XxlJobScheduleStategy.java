package cn.seven.dailypusher.daily.domain.content.schedule.xxljob;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import cn.seven.dailypusher.common.base.exception.rest.ParamErrorException;
import cn.seven.dailypusher.daily.domain.content.schedule.ContentScheduleEntity;
import cn.seven.dailypusher.daily.domain.content.schedule.ContentScheduleParam;
import cn.seven.dailypusher.daily.domain.content.schedule.ScheduleStategy;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentScheduleRequest;
import cn.seven.dailypusher.daily.infrastructure.utils.CronUtil;
import cn.seven.dailypusher.schedule.domain.schedule.ScheduledJobExecutorParam;
import cn.seven.dailypusher.schedule.domain.schedule.xxljob.XxlJobScheduleService;
import cn.seven.dailypusher.schedule.infrastructure.client.request.XxlJobScheduleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class XxlJobScheduleStategy implements ScheduleStategy {
    private final XxlJobScheduleService xxlJobScheduleService;

    @Override
    public ContentScheduleParam createJob(Long contentId, ContentScheduleRequest request) {
        if (!StringUtils.hasText(request.getScheduledPushDayTime())) {
            throw new ParamErrorException("ScheduledPushDayTime不能为空");
        }
        if (request.getScheduledPushWeekDayPattern() == null) {
            throw new ParamErrorException("ScheduledPushWeekDayPattern不能为空");
        }
        ContentScheduleParam contentScheduleParam = new ContentScheduleParam();
        XxlJobScheduleRequest xxlJobScheduleRequest = buildScheduleRequest(contentId, request);
        Integer jobId = xxlJobScheduleService.createJob(xxlJobScheduleRequest);
        String cron = CronUtil.buildCron(request.getScheduledPushWeekDayPattern(), request.getScheduledPushDayTime());
        contentScheduleParam.setCron(cron);
        contentScheduleParam.setXxlJobId(jobId);
        return contentScheduleParam;
    }

    @Override
    public void cacelOldJob(ContentScheduleParam scheduleParam) {
        log.trace("取消xxl-job定时任务");
        xxlJobScheduleService.deleteJob(scheduleParam.getXxlJobId());
    }

    @Override
    public ScheduleType supportedType() {
        return ScheduleType.XXL_JOB_CRON;
    }

    @Override
    public ContentScheduleParam createOrUpdateNewJob(ContentScheduleRequest request, ContentScheduleEntity oldContentSchedule) {
        Long contentId = oldContentSchedule.getContentId();
        ContentScheduleParam contentScheduleParam = new ContentScheduleParam();
        XxlJobScheduleRequest params = buildScheduleRequest(contentId, request);
        Integer jobId;
        if (needCreateJob(oldContentSchedule.getScheduleType())) {
            log.trace("创建xxl-job定时任务");
            jobId = xxlJobScheduleService.createJob(params);
        } else {
            log.trace("更新xxl-job定时任务");
            jobId = oldContentSchedule.getScheduleParam().getXxlJobId();
            xxlJobScheduleService.updateJob(jobId, params);
        }
        contentScheduleParam.setXxlJobId(jobId);
        String cron = CronUtil.buildCron(request.getScheduledPushWeekDayPattern(), request.getScheduledPushDayTime());
        contentScheduleParam.setCron(cron);
        return contentScheduleParam;
    }

    private boolean needCreateJob(ScheduleType oldType) {
        return oldType != ScheduleType.XXL_JOB_CRON;
    }

    private XxlJobScheduleRequest buildScheduleRequest(Long contentId, ContentScheduleRequest request) {
        ScheduledJobExecutorParam executorParam = new ScheduledJobExecutorParam()
                .setContentId(contentId);
        String cron = CronUtil.buildCron(request.getScheduledPushWeekDayPattern(), request.getScheduledPushDayTime());
        XxlJobScheduleRequest xxlJobScheduleRequest = new XxlJobScheduleRequest()
                .setJobDesc(request.getJobDesc())
                .setCron(cron)
                .setExecutorParam(executorParam)
                // TOOD 负责人
                .setAuthor("123")
                .setExecutorHandlerName(XxlJobPushHandler.HANDLER_NAME);
        xxlJobScheduleRequest.setStartRightNow(true);
        return xxlJobScheduleRequest;
    }
}
