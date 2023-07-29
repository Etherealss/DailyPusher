package cn.seven.dailypusher.daily.domain.content;

import cn.seven.dailypusher.common.base.exception.service.NotFoundException;
import cn.seven.dailypusher.common.base.pojo.dto.PageDTO;
import cn.seven.dailypusher.daily.domain.content.schedule.ContentScheduleService;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentScheduleRequest;
import cn.seven.dailypusher.daily.infrastructure.client.response.ContentResponse;
import cn.seven.dailypusher.daily.infrastructure.client.response.ContentScheduleResponse;
import cn.seven.dailypusher.daily.infrastructure.converter.ContentConverter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 简报
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ContentService extends ServiceImpl<ContentRepository, ContentEntity> {
    private final ContentConverter contentConverter;
    private final ContentScheduleService contentScheduleService;

    public ContentResponse getById(Long id) {
        ContentEntity contentEntity = this.lambdaQuery()
                .eq(ContentEntity::getId, id)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(ContentEntity.class, id.toString()));
        ContentScheduleResponse contentSchedule = contentScheduleService.getByContentId(id);
        ContentResponse contentResponse = contentConverter.toResponse(contentEntity)
                .setScheduleType(contentSchedule.getScheduleType())
                .setScheduledPushTime(contentSchedule.getScheduledPushTime())
                .setScheduledPushCron(contentSchedule.getScheduledPushCron());
        return contentResponse;
    }

    public PageDTO<ContentResponse> page(int currentPage, int size) {
        Page<ContentEntity> page = lambdaQuery()
                .page(new Page<>(currentPage, size));
        List<ContentResponse> collect = page.getRecords().stream()
                .map(contentConverter::toResponse)
                .collect(Collectors.toList());
        return new PageDTO<>(collect, page);
    }


    public void createJob(Long contentId, ContentScheduleRequest request) {
        checkContentExist(contentId);
        contentScheduleService.createJob(contentId, request);
    }

    private void checkContentExist(Long contentId) {
        this.lambdaQuery()
                .eq(ContentEntity::getId, contentId)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(ContentEntity.class, contentId.toString()));
    }

    public void updateJob(Long contentId, ContentScheduleRequest request) {
        checkContentExist(contentId);
        contentScheduleService.updateJob(contentId, request);
    }

    public void deleteJob(Long contentId) {
        contentScheduleService.deleteJob(contentId);
    }

    public void runJob(Long contentId) {
        contentScheduleService.runJob(contentId);
    }

    public void stopJob(Long contentId) {
        contentScheduleService.stopJob(contentId);
    }
}
