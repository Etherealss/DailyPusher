package cn.seven.dailypusher.weather.vo;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WeatherVo {
    private String date;//时间

    private String city; //城市名

    private String week; //星期几

    private String wea; //天气

    private String wea_img; //天气图标

    private String tem; //气温

    private String air; //空气质量

    private String humidity; // 湿度
}