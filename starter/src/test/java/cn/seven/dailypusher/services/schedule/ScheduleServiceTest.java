package cn.seven.dailypusher.services.schedule;

import cn.seven.dailypusher.Application;
import cn.seven.dailypusher.schedule.domain.schedule.ScheduleService;
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
class ScheduleServiceTest {
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ScheduleXxlConfig scheduleXxlConfig;

    @Test
    void testCreateJob() {
        ScheduleParam param = new ScheduleParam()
                .setAuthor("测试人")
                .setJobDesc("测试！")
                .setAlarmEmail("123@qq.com")
                .setCron("* * * * * ?");
        String taskId = scheduleService.createJob(param);
        Assertions.assertNotEquals(taskId, "");
    }

    @Test
    void testUpdateJob() {
        ScheduleParam param = new ScheduleParam()
                .setAuthor("测试人")
                .setJobDesc("测试 更新！")
                .setAlarmEmail("123@qq.com")
                .setCron("* * * * * ?");
        scheduleService.updateJob(6, param);
    }

    @Test
    void testRunJob() {
        scheduleService.runJob(6);
    }

    @Test
    void testStopJob() {
        scheduleService.stopJob(6);
    }

    @Test
    void testDeleteJob() {
        scheduleService.deleteJob(6);
    }
}