package cn.seven.dailypusher.daily.domain.content.schedule.xxljob;

import cn.seven.dailypusher.common.base.utils.JsonUtil;
import cn.seven.dailypusher.daily.infrastructure.event.PushContentEvent;
import cn.seven.dailypusher.schedule.domain.schedule.ScheduledJobExecutorParam;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 定时推送
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class XxlJobPushHandler extends IJobHandler {

    public static final String HANDLER_NAME = "scheduledPushHanlder";

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    @XxlJob(value = XxlJobPushHandler.HANDLER_NAME)
    public void execute() {
        String jobParam = XxlJobHelper.getJobParam();
        log.info("定时推送接口执行，参数：{}", jobParam);
        try {
            ScheduledJobExecutorParam executorParam = JsonUtil.toObject(jobParam, ScheduledJobExecutorParam.class);
            Long contentId = executorParam.getContentId();
            applicationEventPublisher.publishEvent(new PushContentEvent(contentId));
        } catch (Exception e) {
            handlerException(e);
        }
    }

    private void handlerException(Exception e) {
        log.warn("定时任务执行失败", e);
    }

}
