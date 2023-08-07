package cn.seven.dailypusher.schedule.domain.schedule.spring;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import cn.seven.dailypusher.schedule.domain.schedule.ScheduleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SpringScheduleService implements ScheduleService {

    private final Snowflake snowflake = IdUtil.getSnowflake();

    private final SpringJobManager springJobManager;
    private final SpringJobCache springJobCache;

    @PostConstruct
    public void initJobs() {
    }

    public Long createAndRunJob(Date executionTime, SpringJobCallback springJobCallback) {
        long jobId = snowflake.nextId();
        springJobCache.setJob(jobId, executionTime);
        springJobManager.createJob(new SpringJobParam()
                .setSpringJobCallback(springJobCallback)
                .setJobId(jobId)
                .setExecutionTime(executionTime));
        return jobId;
    }

    public void updateJob(Long jobId, Date executionTime, SpringJobCallback springJobCallback) {
        springJobCache.setJob(jobId, executionTime);
        springJobManager.cancelJob(jobId);
        springJobManager.createJob(new SpringJobParam()
                .setSpringJobCallback(springJobCallback)
                .setJobId(jobId)
                .setExecutionTime(executionTime));
    }

    public void cancelJob(Long jobId) {
        springJobCache.removeJob(jobId);
        springJobManager.cancelJob(jobId);
    }

    @Scheduled(cron = "0 * * * * ?")
    public void reportJobs() {
        springJobManager.reportJobs();
    }
}
