package cn.seven.dailypusher.schedule.domain.schedule.spring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SpringJobCache {
    public static final String JOB_KEY_PREFIX = "DailyPusher:set:cache:schedule:jobsCache";
    private final RedisTemplate<String, SpringJobParam> redisTemplate;

    public void setJob(Long jobId, Date executionTime) {
//        SpringJobParam springJobParam = new SpringJobParam().setJobId(jobId).setExecutionTime(executionTime);
//        redisTemplate.opsForSet().add(JOB_KEY_PREFIX, springJobParam);
    }

    public void removeJob(Long jobId) {
//        redisTemplate.opsForSet().remove()
    }

    public List<SpringJobParam> getJobs() {
//        redisTemplate.keys(JOB_KEY_PREFIX + "*");
        return null;
    }
}
