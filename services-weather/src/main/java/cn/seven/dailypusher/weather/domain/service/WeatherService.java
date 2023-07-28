package cn.seven.dailypusher.weather.domain.service;

import cn.seven.dailypusher.weather.infrastructure.client.WeatherRequest;
import cn.seven.dailypusher.weather.infrastructure.client.WeatherResponse;
import com.alibaba.fastjson.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class WeatherService {
    private static final String appid = "67783743";
    private static final String appsecret = "fI8ivzPD";


    private final RestTemplate restTemplate;

    public WeatherResponse getWeatherTest(WeatherRequest weatherRequest) {
        String url = "https://v0.yiketianqi.com/free/day?appid={appid}&appsecret={appsecret}&unescape=1&city={city}&&cityid={cityid}";
        Map<String,String> map=new HashMap<>();
        map.put("appid",appid);
        map.put("appsecret",appsecret);
        map.put("city", weatherRequest.getCity());
        map.put("cityid", weatherRequest.getCityId());
        String json = restTemplate.getForObject(url, String.class, map);
        JSONObject jsonObject = JSONObject.parseObject(json);
//        System.out.println(jsonObject);
        String city = jsonObject.getString("city");
        String date = jsonObject.getString("date");
        String week = jsonObject.getString("week");
        String wea = jsonObject.getString("wea");
        String weaImg = jsonObject.getString("wea_img");
        String tem = jsonObject.getString("tem");
        String air = jsonObject.getString("air");
        String humidity = jsonObject.getString("humidity");
        WeatherResponse weatherResponse = WeatherResponse.builder().city(city).date(date).week(week).wea(wea).wea_img(weaImg).tem(tem).air(air).humidity(humidity).build();


        return weatherResponse;

    }


}
