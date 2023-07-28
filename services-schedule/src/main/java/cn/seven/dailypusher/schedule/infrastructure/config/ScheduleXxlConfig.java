package cn.seven.dailypusher.schedule.infrastructure.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Configuration
@ConfigurationProperties("app.schedule.xxl")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class ScheduleXxlConfig {
    Integer groupId;
}
