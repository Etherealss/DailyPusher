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
    private String date;//时间

    private String city; //城市名

    private String week; //星期几

    private String wea; //天气

    private String wea_img; //天气图标

    private String tem; //气温

    private String air; //空气质量

    private String humidity; // 湿度

}
