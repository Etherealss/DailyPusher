package cn.seven.dailypusher.schedule.domain.push;

import cn.seven.dailypush.push.domain.PushService;
import cn.seven.dailypusher.weather.domain.service.WeatherService;
import cn.seven.dailypusher.weather.infrastructure.client.WeatherRequest;
import cn.seven.dailypusher.weather.infrastructure.client.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class WeatherPushService {
    private final PushService pushService;
    private final WeatherService weatherService;

    public void push() {
        WeatherRequest weatherRequest =  new WeatherRequest()
                .setCity("温州")
                .setCityId("101210701");
        WeatherResponse weather = weatherService.getWeather(weatherRequest);
        pushService.push(weather.toString());
    }

}
