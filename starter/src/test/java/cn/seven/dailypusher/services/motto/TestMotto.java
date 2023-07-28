package cn.seven.dailypusher.services.motto;

import cn.seven.dailypusher.Application;
import cn.seven.dailypusher.motto.domain.service.MottoService;
import cn.seven.dailypusher.motto.infrastructure.client.MottoResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j(topic = "test")
@DisplayName("MottoServiceTest测试")
@SpringBootTest(classes = Application.class)
public class TestMotto {

    @Autowired
    MottoService mottoService;

    @Test
    void testMotto() {
        log.info("测试每日一句接口");
        MottoResponse motto = mottoService.getMotto();
        System.out.println(motto.toString());
    }
}
