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
        ContentScheduleEntity entity = this.lambdaQuery()
                .eq(ContentScheduleEntity::getContentId, contentId)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(ContentScheduleEntity.class, contentId.toString()));
        return contentScheduleConverter.toResponse(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createJob(Long contentId, ContentScheduleRequest request) {
        Integer jobId = null;
        if (request.getScheduleType() == ScheduleType.XXL_JOB_CRON) {
            if (!StringUtils.hasText(request.getScheduledPushCron())) {
                throw new ParamErrorException("cron表达式不能为空");
            }
            ScheduleRequest scheduleRequest = buildScheduleRequest(contentId, request);
            jobId = scheduledJobService.createJob(scheduleRequest);
        } else if (request.getScheduleType() == ScheduleType.SPRING_DAY) {
            if (request.getScheduledPushTime() == null) {
                throw new ParamErrorException("ScheduledPushTime不能为空");
            }
        } else {
            throw new ParamErrorException("不支持的ScheduleType");
        }
        ContentScheduleEntity entity = contentScheduleConverter.toEntity(request)
                .setJobId(String.valueOf(jobId));
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
        return scheduleRequest;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateJob(Long contentId, ContentScheduleRequest request) {
        ContentScheduleEntity oldEntity = this.lambdaQuery()
                .eq(ContentScheduleEntity::getContentId, contentId)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(ContentScheduleEntity.class, contentId.toString()));
        Long contentScheduleId = oldEntity.getId();
        if (needCacelOldJob(oldEntity.getScheduleType(), request.getScheduleType())) {
            doCacelOldJob(oldEntity);
        }
        String jobId = createOrUpdateNewJob(request, oldEntity);
        ContentScheduleEntity updatedEntity = contentScheduleConverter.toEntity(request)
                .setJobId(jobId);
        updatedEntity.setId(contentScheduleId);
        this.save(updatedEntity);
    }

    private String createOrUpdateNewJob(ContentScheduleRequest request, ContentScheduleEntity oldContentSchedule) {
        Long contentId = oldContentSchedule.getContentId();

        switch (request.getScheduleType()) {
            case SPRING_DAY:
                // TODO Spring定时任务
                return null;
            case XXL_JOB_CRON:
                ScheduleRequest params = buildScheduleRequest(contentId, request);
                // 如果取消了旧的定时任务，那么就要新建任务；否则更新就好
                if (needCacelOldJob(oldContentSchedule.getScheduleType(), request.getScheduleType())) {
                    Integer jobId = scheduledJobService.createJob(params);
                    return String.valueOf(jobId);
                } else {
                    scheduledJobService.updateJob(Integer.valueOf(oldContentSchedule.getJobId()), params);
                    return oldContentSchedule.getJobId();
                }
            default:
                throw new ParamErrorException("不支持的ScheduleType");
        }
    }

    private void doCacelOldJob(ContentScheduleEntity entity) {
        switch (entity.getScheduleType()) {
            case SPRING_DAY:
                // TODO Spring定时任务
                break;
            case XXL_JOB_CRON:
                scheduledJobService.deleteJob(Integer.valueOf(entity.getJobId()));
                break;
            default:
                throw new ParamErrorException("不支持的ScheduleType");
        }
    }

    private boolean needCacelOldJob(ScheduleType oldType, ScheduleType curType) {
        return oldType != curType;
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteJob(Long contentId) {
        String jobId = this.getJobIdByContentId(contentId);
        scheduledJobService.deleteJob(Integer.valueOf(jobId));
        // TODO Spring定时任务

        this.lambdaUpdate()
                .eq(ContentScheduleEntity::getJobId, jobId)
                .remove();
    }

    public void runJob(Long contentId) {
        String jobId = this.getJobIdByContentId(contentId);
        scheduledJobService.runJob(Integer.valueOf(jobId));
        // TODO Spring定时任务
    }

    public void stopJob(Long contentId) {
        String jobId = this.getJobIdByContentId(contentId);
        scheduledJobService.stopJob(Integer.valueOf(jobId));
        // TODO Spring定时任务
    }

    private String getJobIdByContentId(Long contentId) {
        ContentScheduleEntity entity = this.lambdaQuery()
                .eq(ContentScheduleEntity::getContentId, contentId)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(ContentScheduleEntity.class, contentId.toString()));
        return entity.getJobId();
    }
}
