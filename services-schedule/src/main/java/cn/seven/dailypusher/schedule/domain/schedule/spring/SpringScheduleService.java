package cn.seven.dailypusher.schedule.domain.schedule.spring;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SpringScheduleService {

    private final SpringJobManager springJobManager;
    private final Snowflake snowflake = IdUtil.getSnowflake();

    public Long createAndRunJob(Date executionTime, SpringJobCallback springJobCallback) {
        long jobId = snowflake.nextId();
        springJobManager.createJob(jobId, executionTime, springJobCallback);
        return jobId;
    }

    public void updateJob(Long jobId, Date executionTime, SpringJobCallback springJobCallback) {
        springJobManager.cancelJob(jobId);
        springJobManager.createJob(jobId, executionTime, springJobCallback);
    }

    public void cancelJob(Long jobId) {
        springJobManager.cancelJob(jobId);
    }

    @Scheduled(cron = "0/30 * * * * ?")
    public void reportJobs() {
        springJobManager.reportJobs();
    }
}
