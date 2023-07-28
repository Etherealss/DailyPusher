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
public class WeatherRequest {
    String city; // 城市名，如：温州

    String cityId; // 城市ID，如：101210701
}
