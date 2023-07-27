package cn.seven.dailypusher.common.cron.config.feign;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.form.FormEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 王腾坤
 * @date 2023/7/27
 */
@Configuration
public class XxlFeignConfig {
    @Bean
    public Encoder xxlFeignFormEncoder() {
        return new FormEncoder();
    }

    @Bean
    public Decoder xxlFeignDecoder() {
        return new XxlFeignResultDecoder();
    }

    @Bean
    public ErrorDecoder xxlFeignErrorDecoder() {
        return new XxlFeignResultErrorDecoder(new ErrorDecoder.Default());
    }
}
