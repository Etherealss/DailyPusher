package cn.seven.dailypusher.services.weather;

import cn.seven.dailypusher.Application;

import cn.seven.dailypusher.weather.domain.service.WeatherService;
import cn.seven.dailypusher.weather.infrastructure.client.WeatherRequest;
import cn.seven.dailypusher.weather.infrastructure.client.WeatherResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j(topic = "test")
@DisplayName("WeatherServiceTest测试")
@SpringBootTest(classes = Application.class)
public class WeatherTest {

    @Autowired
    WeatherService weatherService;

    @Test
    void testWeather() {
        log.info("测试天气接口");
//        WeatherVo weatherTest = weatherService.getWeatherTest(WeatherConstant.WU_HAN);
//        log.info("天气接口返回结果：{}", weatherTest.getCity());
        WeatherResponse weatherResponse = weatherService.getWeatherTest(WeatherRequest.builder().city("武汉").cityId("101200101").build());
        log.info("天气接口返回结果：{}", weatherResponse.toString());
    }
}
