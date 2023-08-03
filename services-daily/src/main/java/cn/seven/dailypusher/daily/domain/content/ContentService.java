package cn.seven.dailypusher.daily.domain.content;

import cn.seven.dailypusher.common.base.exception.service.NotFoundException;
import cn.seven.dailypusher.common.base.pojo.dto.PageDTO;
import cn.seven.dailypusher.daily.domain.content.arrangement.ContentArrangementService;
import cn.seven.dailypusher.daily.domain.content.push.ContentPushService;
import cn.seven.dailypusher.daily.domain.content.schedule.ContentScheduleService;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentRequest;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentScheduleRequest;
import cn.seven.dailypusher.daily.infrastructure.client.response.ContentResponse;
import cn.seven.dailypusher.daily.infrastructure.client.response.ContentScheduleResponse;
import cn.seven.dailypusher.daily.infrastructure.converter.ContentConverter;
import cn.seven.dailypusher.daily.infrastructure.utils.CronUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    private final ContentArrangementService contentArrangementService;
    private final ContentPushService contentPushService;

    @Transactional(rollbackFor = Exception.class)
    public Long create(ContentRequest contentRequest) {
        ContentEntity entity = contentConverter.toEntity(contentRequest);
        this.save(entity);
        Long contentId = entity.getId();
        // 创建定时任务
        ContentScheduleRequest contentScheduleRequest = contentConverter.toScheduleRequest(contentRequest);
        contentScheduleRequest.setJobDesc(contentRequest.getContentName());
        contentScheduleService.createJob(contentId, contentScheduleRequest);
        return contentId;
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(Long contentId, ContentRequest contentRequest) {
        ContentEntity entity = contentConverter.toEntity(contentRequest);
        entity.setId(contentId);

        if (contentRequest.getScheduleType() != null) {
            ContentScheduleRequest contentScheduleRequest = contentConverter.toScheduleRequest(contentRequest);
            contentScheduleRequest.setJobDesc(contentRequest.getContentName());
            contentScheduleService.updateJob(contentId, contentScheduleRequest);
        }
    }

    public ContentResponse getById(Long id) {
        ContentEntity contentEntity = this.lambdaQuery()
                .eq(ContentEntity::getId, id)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(ContentEntity.class, id.toString()));
        return completeContentScheduleAndResponse(contentEntity);
    }

    public PageDTO<ContentResponse> page(int currentPage, int size) {
        Page<ContentEntity> page = lambdaQuery()
                .page(new Page<>(currentPage, size));
        List<ContentResponse> collect = page.getRecords().stream()
                .map(this::completeContentScheduleAndResponse)
                .collect(Collectors.toList());
        return new PageDTO<>(collect, page);
    }

    private ContentResponse completeContentScheduleAndResponse(ContentEntity content) {
        ContentScheduleResponse contentSchedule = contentScheduleService.getByContentId(content.getId());
        ContentResponse contentResponse = contentConverter.toResponse(content)
                .setScheduleType(contentSchedule.getScheduleType())
                .setScheduledPushDateTime(contentSchedule.getScheduleParam().getTime());
        String cron = contentSchedule.getScheduleParam().getCron();
        if (StringUtils.hasText(cron)) {
            contentResponse.setScheduledPushDayTime(CronUtil.toDayTime(cron));
            contentResponse.setScheduledPushWeekDayPattern(CronUtil.toWeekDayPattern(cron));
        }
        return contentResponse;
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

    @Transactional(rollbackFor = Exception.class)
    public void delete(Long contentId) {
        contentScheduleService.deleteJob(contentId);
        this.removeById(contentId);
    }

    public ContentScheduleResponse getContentSchedule(Long contentId) {
        checkContentExist(contentId);
        return contentScheduleService.getByContentId(contentId);
    }

    public void pushContent(Long contentId) {
        ContentResponse contentResponse = this.getById(contentId);
        String contentTxt = contentArrangementService.arrangement(contentResponse);
        log.debug("推送内容：{}。内容文本：{}", contentId, contentTxt);
        contentPushService.push(contentResponse.getEnterpriseWeChatHookKeys(), contentTxt);
    }
}
