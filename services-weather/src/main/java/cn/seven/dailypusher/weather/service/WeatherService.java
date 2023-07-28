package cn.seven.dailypusher.weather.service;

import cn.seven.dailypusher.weather.dto.constant.WeatherConstant;
import cn.seven.dailypusher.weather.vo.WeatherVo;
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

    public WeatherVo getWeatherTest(WeatherConstant weatherConstant) {
        String url = "https://v0.yiketianqi.com/free/day?appid={appid}&appsecret={appsecret}&unescape=1&city={city}&&cityid={cityid}";
        Map<String,String> map=new HashMap<>();
        map.put("appid",appid);
        map.put("appsecret",appsecret);
        map.put("city",weatherConstant.getCityName());
        map.put("cityid",weatherConstant.getCode());
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
        WeatherVo weatherVo = WeatherVo.builder().city(city).date(date).week(week).wea(wea).wea_img(weaImg).tem(tem).air(air).humidity(humidity).build();


        return weatherVo;

    }


}
