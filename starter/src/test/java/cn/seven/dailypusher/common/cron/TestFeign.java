package cn.seven.dailypusher.common.cron;

import cn.hutool.core.util.IdUtil;
import cn.seven.dailypusher.Application;
import cn.seven.dailypusher.common.cron.remote.param.XxlJobLoginParam;
import cn.seven.dailypusher.common.cron.remote.XxlJobFeign;
import feign.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;

/**
 * @author 王腾坤
 * @date 2023/7/27
 */
@Slf4j
@SpringBootTest(classes = Application.class)
public class TestFeign {
    @Autowired
    XxlJobFeign xxlJobFeign;

    @Test
    void test() {
        XxlJobLoginParam param = new XxlJobLoginParam()
                .setUserName("admin")
                .setPassword("123456")
                .setRandomCode(IdUtil.fastSimpleUUID());
        Response response = xxlJobFeign.login(param);
        Collection<String> cookies = response.headers().get("set-cookie");
        log.info("cookies:{}", cookies);
    }
}
