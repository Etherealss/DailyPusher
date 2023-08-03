package cn.seven.dailypusher.schedule.domain.schedule.spring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Objects;
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
    private final Map<Long, Date> executionTimes = new ConcurrentHashMap<>();
    private final TaskScheduler taskScheduler;

    /**
     * 添加定时任务
     * @param springJobParam
     */
    public void createJob(SpringJobParam springJobParam) {
        Date executionTime = springJobParam.getExecutionTime();
        Long jobId = springJobParam.getJobId();
        Objects.requireNonNull(jobId);
        Objects.requireNonNull(executionTime);
        log.debug("内容定时推送日期：{}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(executionTime));
        ScheduledFuture<?> scheduledFuture = taskScheduler.schedule(
                new SpringRuunableJob(springJobParam.getSpringJobCallback()),
                executionTime);
        runningJobs.put(jobId, scheduledFuture);
        executionTimes.put(jobId, executionTime);
    }

    /**
     * 取消定时任务
     * @param jobId 任务ID
     */
    public void cancelJob(Long jobId) {
        Objects.requireNonNull(jobId, "取消Spring定时任务时jobId不能为空");
        // 从任务映射中获取定时任务，并取消任务
        ScheduledFuture<?> scheduledFuture = runningJobs.remove(jobId);
        if (scheduledFuture != null) {
            scheduledFuture.cancel(true);
        }
        executionTimes.remove(jobId);
    }

    public void reportJobs() {
        if (executionTimes.isEmpty()) {
            log.info("当前spring定时任务数量为 0");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Long, Date> entry : executionTimes.entrySet()) {
            sb.append("定时任务ID：").append(entry.getKey())
                    .append(":").append(entry.getValue())
                    .append("\n");
        }
        log.info("当前spring定时任务信息：\n{}", sb.toString());
    }
}
