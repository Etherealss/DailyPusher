package cn.seven.dailypusher.event;

import cn.seven.dailypusher.Application;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

/**
 * @author 王腾坤
 * @date 2023/8/2
 */
@Slf4j(topic = "test")
@DisplayName("EventExceptionTest测试")
@SpringBootTest(classes = Application.class)
public class EventExceptionTest {
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Test
    void publishEvent() {
        Assertions.assertThrows(RuntimeException.class, () -> {
            applicationEventPublisher.publishEvent(new MyEvent());
        });
        log.info("执行完毕");
    }
}
