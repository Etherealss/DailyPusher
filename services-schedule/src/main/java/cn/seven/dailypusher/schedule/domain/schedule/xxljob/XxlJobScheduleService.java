package cn.seven.dailypusher.schedule.domain.schedule.xxljob;

import cn.seven.dailypusher.common.base.utils.JsonUtil;
import cn.seven.dailypusher.common.cron.constants.XxlJobConstant;
import cn.seven.dailypusher.common.cron.enums.ExecutorRouteStrategyEnum;
import cn.seven.dailypusher.common.cron.enums.MisfireStrategyEnum;
import cn.seven.dailypusher.common.cron.enums.XxlScheduleType;
import cn.seven.dailypusher.common.cron.remote.param.XxlJobInfo;
import cn.seven.dailypusher.common.cron.service.XxlJobService;
import cn.seven.dailypusher.schedule.infrastructure.client.request.XxlJobScheduleRequest;
import cn.seven.dailypusher.schedule.infrastructure.config.ScheduleXxlConfig;
import com.xxl.job.core.enums.ExecutorBlockStrategyEnum;
import com.xxl.job.core.glue.GlueTypeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class XxlJobScheduleService {
    private final XxlJobService xxlJobService;
    private final ScheduleXxlConfig scheduleXxlConfig;

    public Integer createJob(XxlJobScheduleRequest params) {
        XxlJobInfo xxlJobInfo = buildXxlJobInfo(params);
        return xxlJobService.createJob(xxlJobInfo);
    }

    private XxlJobInfo initInfo() {
        return new XxlJobInfo()
                .setJobGroup(scheduleXxlConfig.getGroupId())
                .setScheduleType(XxlScheduleType.CRON.name())
                .setMisfireStrategy(MisfireStrategyEnum.DO_NOTHING.name())
                .setExecutorRouteStrategy(ExecutorRouteStrategyEnum.FIRST.name())
                .setExecutorBlockStrategy(ExecutorBlockStrategyEnum.SERIAL_EXECUTION.name())
                .setExecutorTimeout(XxlJobConstant.TIME_OUT)
                .setExecutorFailRetryCount(XxlJobConstant.RETRY_COUNT)
                .setGlueType(GlueTypeEnum.BEAN.name());
    }

    public void updateJob(Integer jobId, XxlJobScheduleRequest params) {
        XxlJobInfo xxlJobInfo = buildXxlJobInfo(params);
        xxlJobInfo.setId(jobId);
        xxlJobService.updateJob(xxlJobInfo);
    }

    private XxlJobInfo buildXxlJobInfo(XxlJobScheduleRequest params) {
        int triggerStatus = params.getStartRightNow() ?
                XxlJobConstant.TRIGGER_STATUS_START : XxlJobConstant.TRIGGER_STATUS_STOP;
        XxlJobInfo xxlJobInfo = this.initInfo()
                .setJobDesc(params.getJobDesc())
                .setAuthor(params.getAuthor())
                .setScheduleConf(params.getCron())
                .setExecutorHandler(params.getExecutorHandlerName())
                .setExecutorParam(JsonUtil.toJsonString(params.getExecutorParam()))
                .setTriggerStatus(triggerStatus);
        return xxlJobInfo;
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
