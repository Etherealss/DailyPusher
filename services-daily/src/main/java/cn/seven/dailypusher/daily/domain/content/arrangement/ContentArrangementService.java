package cn.seven.dailypusher.daily.domain.content.arrangement;

import cn.seven.dailypusher.daily.infrastructure.client.response.ContentResponse;
import cn.seven.dailypusher.motto.domain.service.MottoService;
import cn.seven.dailypusher.motto.infrastructure.client.MottoResponse;
import cn.seven.dailypusher.weather.domain.service.WeatherService;
import cn.seven.dailypusher.weather.infrastructure.client.WeatherRequest;
import cn.seven.dailypusher.weather.infrastructure.client.WeatherResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 内容编排
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ContentArrangementService {
    private final WeatherService weatherService;
    private final MottoService mottoService;

    public String arrangement(ContentResponse contentResponse) {
        StringBuilder builder = new StringBuilder();
        builder.append(contentResponse.getBriefing()).append("\n");
        if (contentResponse.getContainWeather()) {
            WeatherRequest weatherRequest = new WeatherRequest()
                    .setCity("温州")
                    .setCityId("101210701");
            WeatherResponse weatherResponse = weatherService.getWeather(weatherRequest);
            builder.append(weatherResponse.buildReport()).append("\n");
        }
        if (contentResponse.getContainMotto()) {
            MottoResponse mottoResponse = mottoService.getMotto();
            builder.append(mottoResponse.buildReport()).append("\n");
        }
        return builder.toString();
    }
}
