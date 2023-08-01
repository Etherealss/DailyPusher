package cn.seven.dailypusher.weather.infrastructure.client;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class WeatherResponse {
    String date;//时间

    String city; //城市名

    String week; //星期几

    String wea; //天气

    String wea_img; //天气图标

    String tem; //气温

    String air; //空气质量

    String humidity; // 湿度

    public String buildReport() {
        return "【" + city + "天气预报】\n" +
                "日期：" + date + "\n" +
                "天气：" + wea + "\n" +
                "气温：" + tem + "\n" +
                "空气质量：" + air + "\n" +
                "湿度：" + humidity;
    }
}
