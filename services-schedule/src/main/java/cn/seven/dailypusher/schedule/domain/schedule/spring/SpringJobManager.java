package cn.seven.dailypusher.schedule.domain.schedule.spring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SpringJobManager {

    private final Map<Long, ScheduledFuture<?>> runningJobs = new ConcurrentHashMap<>();
    private final TaskScheduler taskScheduler;

    /**
     * 添加定时任务
     * @param executionTime
     * @param springJobCallback
     */
    public void createJob(Long jobId, Date executionTime, SpringJobCallback springJobCallback) {
        log.debug("内容定时推送日期：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(executionTime));
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(
                new SpringRuunableJob(springJobCallback),
                executionTime);
        runningJobs.put(jobId, scheduledFuture);
    }

    /**
     * 取消定时任务
     * @param jobId 任务ID
     */
    public void cancelJob(Long jobId) {
        // 从任务映射中获取定时任务，并取消任务
        ScheduledFuture<?> scheduledFuture = runningJobs.remove(jobId);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
    }

    public void reportJobs() {
        log.info("当前spring定时任务数量：{}", runningJobs.size());
    }
}
