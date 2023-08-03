package cn.seven.dailypusher.utils;

import cn.seven.dailypusher.Application;
import cn.seven.dailypusher.daily.infrastructure.utils.CronUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j(topic = "test")
@DisplayName("CronUtilTest测试")
@SpringBootTest(classes = Application.class)
class CronUtilTest {
    public static void main(String[] args) {
        for (int i = 1; i <= 127; i++) {
            String cron = CronUtil.buildCron(i, "12:22");
            String time = CronUtil.toDayTime(cron);
            int weekDay = CronUtil.toWeekDayPattern(cron);
            log.info("cron: {},\ttime: {},\tweekDay: {}", cron, time, weekDay);
            Assertions.assertEquals(weekDay, i);
            Assertions.assertEquals(time, "12:22");
        }
    }
}