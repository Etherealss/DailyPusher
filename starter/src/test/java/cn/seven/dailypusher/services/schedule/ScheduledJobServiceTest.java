package cn.seven.dailypusher.services.schedule;

import cn.seven.dailypusher.Application;
import cn.seven.dailypusher.schedule.domain.schedule.ScheduledJobService;
import cn.seven.dailypusher.schedule.domain.schedule.param.ScheduleParam;
import cn.seven.dailypusher.schedule.infrastructure.config.ScheduleXxlConfig;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j(topic = "test")
@DisplayName("ScheduleServiceTest测试")
@SpringBootTest(classes = Application.class)
class ScheduledJobServiceTest {
    @Autowired
    private ScheduledJobService scheduledJobService;
    @Autowired
    private ScheduleXxlConfig scheduleXxlConfig;

    @Test
    void testCreateJob() {
        ScheduleParam param = new ScheduleParam()
                .setAuthor("测试人")
                .setJobDesc("测试！")
                .setAlarmEmail("123@qq.com")
                .setCron("* * * * * ?");
        Integer jobId = scheduledJobService.createJob(param);
        Assertions.assertNotNull(jobId);
    }

    @Test
    void testUpdateJob() {
        ScheduleParam param = new ScheduleParam()
                .setAuthor("测试人")
                .setJobDesc("测试 更新！")
                .setAlarmEmail("123@qq.com")
                .setCron("* * * * * ?");
        scheduledJobService.updateJob(6, param);
    }

    @Test
    void testRunJob() {
        scheduledJobService.runJob(6);
    }

    @Test
    void testStopJob() {
        scheduledJobService.stopJob(6);
    }

    @Test
    void testDeleteJob() {
        scheduledJobService.deleteJob(6);
    }
}