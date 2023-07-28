package cn.seven.dailypush.push.infrastructure.config;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Configuration
@ConfigurationProperties("app.push.enterprise-wechat")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Validated
public class EnterpriseWeChatConfig {
    @NotBlank
    String webhookKey;
}
