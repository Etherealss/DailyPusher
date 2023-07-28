package cn.seven.dailypusher.services.motto;

import cn.seven.dailypusher.Application;
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
    BriefService mottoService;

    @Test
    void testMotto() {
        log.info("测试每日一句接口");
        MottoVo motto = mottoService.getMotto();
        System.out.println(motto.getContent());
    }
}
