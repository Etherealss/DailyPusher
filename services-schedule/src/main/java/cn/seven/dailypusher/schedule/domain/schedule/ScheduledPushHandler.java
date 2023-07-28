package cn.seven.dailypusher.schedule.domain.schedule;

import cn.seven.dailypusher.common.base.utils.JsonUtil;
import cn.seven.dailypusher.schedule.domain.push.BriefingPushService;
import cn.seven.dailypusher.schedule.domain.push.MottoPushService;
import cn.seven.dailypusher.schedule.domain.push.WeatherPushService;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时推送
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class ScheduledPushHandler extends IJobHandler {

    public static final String HANDLER_NAME = "scheduledPushHanlder";

    private final BriefingPushService briefingPushService;
    private final MottoPushService mottoPushService;
    private final WeatherPushService weatherPushService;

    @Override
    @XxlJob(value = ScheduledPushHandler.HANDLER_NAME)
    public void execute() {
        String jobParam = XxlJobHelper.getJobParam();
        log.info("定时推送接口执行，参数：{}", jobParam);
        try {
            JobExecuteParam jobExecuteParam = JsonUtil.toObject(jobParam, JobExecuteParam.class);
//            briefingPushService.push(jobExecuteParam.getContentId());
//            mottoPushService.push();
            weatherPushService.push();
        } catch (Exception e) {
            handlerException(e);
        }
    }

    private void handlerException(Exception e) {
        log.warn("定时任务执行失败", e);
    }

}
