package cn.seven.dailypusher.schedule.domain.schedule;

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

    @Override
    @XxlJob(value = ScheduledPushHandler.HANDLER_NAME)
    public void execute() throws Exception {
        String jobParam = XxlJobHelper.getJobParam();
        log.info("定时推送接口执行，参数：{}", jobParam);
    }

}
