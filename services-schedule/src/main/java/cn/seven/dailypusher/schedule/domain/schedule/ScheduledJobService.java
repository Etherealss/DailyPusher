package cn.seven.dailypusher.schedule.domain.schedule;

import cn.seven.dailypusher.common.cron.constants.XxlJobConstant;
import cn.seven.dailypusher.common.cron.enums.ExecutorRouteStrategyEnum;
import cn.seven.dailypusher.common.cron.enums.MisfireStrategyEnum;
import cn.seven.dailypusher.common.cron.enums.XxlScheduleType;
import cn.seven.dailypusher.common.cron.remote.param.XxlJobInfo;
import cn.seven.dailypusher.common.cron.service.XxlJobService;
import cn.seven.dailypusher.schedule.domain.schedule.param.ScheduleParam;
import cn.seven.dailypusher.schedule.infrastructure.config.ScheduleXxlConfig;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ScheduledJobService {
    private final XxlJobService xxlJobService;
    private final ScheduleXxlConfig scheduleXxlConfig;

    public Integer createJob(ScheduleParam params) {
        XxlJobInfo xxlJobInfo = this.initInfo();
        BeanUtils.copyProperties(params, xxlJobInfo);
        xxlJobInfo.setScheduleConf(params.getCron());
        return xxlJobService.createJob(xxlJobInfo);
    }

    private XxlJobInfo initInfo() {
        return new XxlJobInfo()
                .setJobGroup(scheduleXxlConfig.getGroupId())
                .setScheduleType(XxlScheduleType.CRON.name())
                .setMisfireStrategy(MisfireStrategyEnum.DO_NOTHING.name())
                .setExecutorHandler(ScheduledPushHandler.HANDLER_NAME)
                .setExecutorRouteStrategy(ExecutorRouteStrategyEnum.FIRST.name())
                .setExecutorBlockStrategy(ExecutorBlockStrategyEnum.SERIAL_EXECUTION.name())
                .setExecutorTimeout(XxlJobConstant.TIME_OUT)
                .setExecutorFailRetryCount(XxlJobConstant.RETRY_COUNT)
                .setGlueType(GlueTypeEnum.BEAN.name())
                .setTriggerStatus(XxlJobConstant.TRIGGER_STATUS_STOP);
    }

    public void updateJob(Integer id, ScheduleParam params) {
        XxlJobInfo xxlJobInfo = this.initInfo();
        BeanUtils.copyProperties(params, xxlJobInfo);
        xxlJobInfo.setScheduleConf(params.getCron())
                .setId(id);
        xxlJobService.updateJob(xxlJobInfo);
    }

    public void deleteJob(Integer jobId) {
        xxlJobService.deleteJob(jobId);
    }

    public void runJob(Integer jobId) {
        xxlJobService.runJob(jobId);
    }

    public void stopJob(Integer jobId) {
        xxlJobService.stopJob(jobId);
    }
}
