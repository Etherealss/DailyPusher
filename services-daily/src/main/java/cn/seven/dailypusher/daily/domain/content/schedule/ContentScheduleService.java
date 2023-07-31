package cn.seven.dailypusher.daily.domain.content.schedule;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import cn.seven.dailypusher.common.base.exception.rest.ParamErrorException;
import cn.seven.dailypusher.common.base.exception.service.NotFoundException;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentScheduleRequest;
import cn.seven.dailypusher.daily.infrastructure.client.response.ContentScheduleResponse;
import cn.seven.dailypusher.daily.infrastructure.converter.ContentScheduleConverter;
import cn.seven.dailypusher.schedule.domain.schedule.ScheduledJobExecutorParam;
import cn.seven.dailypusher.schedule.domain.schedule.ScheduledJobService;
import cn.seven.dailypusher.schedule.infrastructure.client.request.ScheduleRequest;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

/**
 * @author 王腾坤
 * @date 2023/7/29
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ContentScheduleService extends ServiceImpl<ContentScheduleRepository, ContentScheduleEntity> {
    private final ScheduledJobService scheduledJobService;
    private final ContentScheduleConverter contentScheduleConverter;

    public ContentScheduleResponse getByContentId(Long contentId) {
        ContentScheduleEntity entity = getContentScheduleEntityOpt(contentId)
                .orElseThrow(() -> new NotFoundException(ContentScheduleEntity.class, contentId.toString()));
        return contentScheduleConverter.toResponse(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createJob(Long contentId, ContentScheduleRequest request) {
        getContentScheduleEntityOpt(contentId)
                .ifPresent((entity) -> {
                    throw new ParamErrorException("content " + contentId + " 已存在定时任务");
                });

        ContentScheduleParam contentScheduleParam = new ContentScheduleParam();
        if (request.getScheduleType() == ScheduleType.XXL_JOB_CRON) {
            if (!StringUtils.hasText(request.getScheduledPushCron())) {
                throw new ParamErrorException("cron表达式不能为空");
            }
            ScheduleRequest scheduleRequest = buildScheduleRequest(contentId, request);
            Integer jobId = scheduledJobService.createJob(scheduleRequest);
            contentScheduleParam.setCron(request.getScheduledPushCron());
            contentScheduleParam.setXxlJobId(jobId);
        } else if (request.getScheduleType() == ScheduleType.SPRING_DAY) {
            if (request.getScheduledPushTime() == null) {
                throw new ParamErrorException("ScheduledPushTime不能为空");
            }
        } else {
            throw new ParamErrorException("不支持的ScheduleType");
        }

        ContentScheduleEntity entity = contentScheduleConverter.toEntity(request);
        entity.setContentId(contentId);
        entity.setScheduleParam(contentScheduleParam);
        this.save(entity);
    }


    private ScheduleRequest buildScheduleRequest(Long contentId, ContentScheduleRequest request) {
        ScheduledJobExecutorParam executorParam = new ScheduledJobExecutorParam()
                .setContentId(contentId);
        ScheduleRequest scheduleRequest = new ScheduleRequest()
                .setJobDesc(request.getJobDesc())
                .setCron(request.getScheduledPushCron())
                .setExecutorParam(executorParam)
                // TOOD 负责人
                .setAuthor("123")
                .setExecutorHandlerName(ScheduledPushHandler.HANDLER_NAME);
        scheduleRequest.setStartRightNow(true);
        return scheduleRequest;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateJob(Long contentId, ContentScheduleRequest request) {
        ContentScheduleEntity oldSchedule = getContentScheduleEntityOpt(contentId)
                .orElseThrow(() -> new NotFoundException(ContentScheduleEntity.class, contentId.toString()));
        if (needCacelOldJob(oldSchedule.getScheduleType(), request.getScheduleType())) {
            cacelOldJob(oldSchedule);
        }
        if (needCreateNewJob(request)) {
            ContentScheduleParam contentScheduleParam = createOrUpdateNewJob(request, oldSchedule);
            updateSchedule(request, oldSchedule, contentScheduleParam);
        } else {
            updateSchedule(contentId, oldSchedule);
        }
    }

    private boolean needCacelOldJob(ScheduleType oldType, ScheduleType curType) {
        return oldType != curType;
    }

    private void cacelOldJob(ContentScheduleEntity oldSchedule) {
        switch (oldSchedule.getScheduleType()) {
            case SPRING_DAY:
                // TODO Spring定时任务
                log.trace("取消Spring定时任务");
                break;
            case XXL_JOB_CRON:
                log.trace("取消xxl-job定时任务");
                scheduledJobService.deleteJob(oldSchedule.getScheduleParam().getXxlJobId());
                break;
            case NO_SCHEDULE:
                break;
            default:
                throw new ParamErrorException("不支持的ScheduleType");
        }
    }

    private static boolean needCreateNewJob(ContentScheduleRequest request) {
        return request.getScheduleType() != ScheduleType.NO_SCHEDULE;
    }

    private ContentScheduleParam createOrUpdateNewJob(ContentScheduleRequest request, ContentScheduleEntity oldContentSchedule) {
        Long contentId = oldContentSchedule.getContentId();
        ContentScheduleParam contentScheduleParam = new ContentScheduleParam();
        switch (request.getScheduleType()) {
            case SPRING_DAY:
                // TODO Spring定时任务
                log.trace("创建Spring定时任务");
                break;
            case XXL_JOB_CRON:
                log.trace("创建xxl-job定时任务");
                ScheduleRequest params = buildScheduleRequest(contentId, request);
                // 如果取消了旧的定时任务，那么就要新建任务；否则更新就好
                Integer jobId;
                if (needCacelOldJob(oldContentSchedule.getScheduleType(), request.getScheduleType())) {
                    jobId = scheduledJobService.createJob(params);
                } else {
                    jobId = oldContentSchedule.getScheduleParam().getXxlJobId();
                    scheduledJobService.updateJob(jobId, params);
                }
                contentScheduleParam.setXxlJobId(jobId);
                contentScheduleParam.setCron(request.getScheduledPushCron());
                break;
            default:
                throw new ParamErrorException("不支持的ScheduleType");
        }
        return contentScheduleParam;
    }

    private void updateSchedule(ContentScheduleRequest request, ContentScheduleEntity oldSchedule, ContentScheduleParam contentScheduleParam) {
        ContentScheduleEntity updatedEntity = contentScheduleConverter.toEntity(request);
        updatedEntity.setScheduleParam(contentScheduleParam);
        this.lambdaUpdate()
                .eq(ContentScheduleEntity::getId, oldSchedule.getId())
                .update(updatedEntity);
    }

    private void updateSchedule(Long contentId, ContentScheduleEntity oldSchedule) {
        ContentScheduleEntity contentScheduleEntity = new ContentScheduleEntity()
                .setScheduleType(ScheduleType.NO_SCHEDULE);
        this.lambdaUpdate()
                .eq(ContentScheduleEntity::getId, oldSchedule.getId())
                .update(contentScheduleEntity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteJob(Long contentId) {
        ContentScheduleEntity entity = getContentScheduleEntityOpt(contentId)
                .orElseThrow(() -> new NotFoundException(ContentScheduleEntity.class, contentId.toString()));
        this.removeById(entity.getId());
        Integer jobId = entity.getScheduleParam().getXxlJobId();
        scheduledJobService.deleteJob(jobId);
    }

    public void runJob(Long contentId) {
        Integer jobId = this.getJobIdByContentId(contentId);
        scheduledJobService.runJob(jobId);
        // TODO Spring定时任务
    }

    public void stopJob(Long contentId) {
        Integer jobId = this.getJobIdByContentId(contentId);
        scheduledJobService.stopJob(jobId);
        // TODO Spring定时任务
    }

    private Integer getJobIdByContentId(Long contentId) {
        ContentScheduleEntity entity = getContentScheduleEntityOpt(contentId)
                .orElseThrow(() -> new NotFoundException(ContentScheduleEntity.class, contentId.toString()));
        return entity.getScheduleParam().getXxlJobId();
    }

    private Optional<ContentScheduleEntity> getContentScheduleEntityOpt(Long contentId) {
        return this.lambdaQuery()
                .eq(ContentScheduleEntity::getContentId, contentId)
                .oneOpt();
    }
}
