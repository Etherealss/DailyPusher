package cn.seven.dailypusher.common.cron.service;

import cn.seven.dailypusher.common.cron.config.XxlJobConfig;
import cn.seven.dailypusher.common.cron.remote.XxlJobFeign;
import cn.seven.dailypusher.common.cron.remote.param.XxlJobLoginParam;
import feign.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collection;
import java.util.UUID;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class XxlJobCookieHolder {
    public static final String COOKIE_HEADER_NAME = "set-cookie";

    private final XxlJobFeign xxlJobFeign;
    private final XxlJobConfig xxlJobConfig;

    private volatile String cookie;

    @PostConstruct
    public void postConstruct() {
        this.getCookie();
    }

    /**
     * 使用单例模式获取Cookie
     * @return
     */
    public String getCookie() {
        if (cookie == null) {
            synchronized (this) {
                if (cookie == null) {
                    cookie = login();
                }
            }
        }
        return cookie;
    }

    private String login() {
        XxlJobLoginParam param = new XxlJobLoginParam()
                .setUserName(xxlJobConfig.getUsername())
                .setPassword(xxlJobConfig.getPassword())
                .setRandomCode(UUID.randomUUID().toString());
        Response response = xxlJobFeign.login(param);
        Collection<String> cookies = response.headers().get(COOKIE_HEADER_NAME);
        return cookies.iterator().next();
    }
}
