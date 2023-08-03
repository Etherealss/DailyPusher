package cn.seven.dailypusher.daily.domain.content.schedule;

import cn.seven.dailypusher.common.base.enums.ScheduleType;
import cn.seven.dailypusher.common.base.exception.rest.ParamErrorException;
import cn.seven.dailypusher.common.base.exception.service.NotFoundException;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentScheduleRequest;
import cn.seven.dailypusher.daily.infrastructure.client.response.ContentScheduleResponse;
import cn.seven.dailypusher.daily.infrastructure.converter.ContentScheduleConverter;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author 王腾坤
 * @date 2023/7/29
 */
@Slf4j
@Service
public class ContentScheduleService extends ServiceImpl<ContentScheduleRepository, ContentScheduleEntity> {
    private final ContentScheduleConverter contentScheduleConverter;
    private final Map<ScheduleType, ScheduleStategy> scheduleStategys;

    public ContentScheduleService(ContentScheduleConverter contentScheduleConverter,
                                  List<ScheduleStategy> scheduleStategys) {
        this.contentScheduleConverter = contentScheduleConverter;
        this.scheduleStategys = scheduleStategys.stream().collect(Collectors.toMap(
                ScheduleStategy::supportedType, Function.identity()
        ));
    }

    private ScheduleStategy routeScheduleStategy(ScheduleType scheduleType) {
        ScheduleStategy scheduleStategy = scheduleStategys.get(scheduleType);
        if (scheduleStategy == null) {
            throw new ParamErrorException("不支持的ScheduleType");
        }
        return scheduleStategy;
    }

    public ContentScheduleResponse getByContentId(Long contentId) {
        ContentScheduleEntity entity = getContentScheduleEntityOpt(contentId)
                .orElseThrow(() -> new NotFoundException(ContentScheduleEntity.class, contentId.toString()));
        return contentScheduleConverter.toResponse(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void createJob(Long contentId, ContentScheduleRequest request) {
        getContentScheduleEntityOpt(contentId).ifPresent((entity) -> {
            throw new ParamErrorException("content " + contentId + " 已存在定时任务");
        });
        ContentScheduleParam contentScheduleParam = routeScheduleStategy(request.getScheduleType())
                .createJob(contentId, request);

        ContentScheduleEntity entity = contentScheduleConverter.toEntity(request);
        entity.setContentId(contentId);
        entity.setScheduleParam(contentScheduleParam);
        this.save(entity);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateJob(Long contentId, ContentScheduleRequest newScheduleRequest) {
        ContentScheduleEntity oldSchedule = getContentScheduleEntityOpt(contentId)
                .orElseThrow(() -> new NotFoundException(ContentScheduleEntity.class, contentId.toString()));
        if (needCacelOldJob(oldSchedule.getScheduleType(), newScheduleRequest.getScheduleType())) {
            routeScheduleStategy(oldSchedule.getScheduleType()).cacelOldJob(oldSchedule.getScheduleParam());
        }
        if (needCreateNewJob(newScheduleRequest)) {
            ContentScheduleParam contentScheduleParam = routeScheduleStategy(newScheduleRequest.getScheduleType())
                    .createOrUpdateNewJob(newScheduleRequest, oldSchedule);
            updateSchedule(newScheduleRequest, oldSchedule, contentScheduleParam);
        } else {
            updateSchedule(contentId, oldSchedule);
        }
    }

    private boolean needCacelOldJob(ScheduleType oldType, ScheduleType curType) {
        return oldType != curType && oldType != ScheduleType.NO_SCHEDULE;
    }

    private static boolean needCreateNewJob(ContentScheduleRequest request) {
        return request.getScheduleType() != ScheduleType.NO_SCHEDULE;
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
        routeScheduleStategy(entity.getScheduleType()).cacelOldJob(entity.getScheduleParam());
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
