package cn.seven.dailypush.push.infrastructure.remote;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.seven.dailypusher.common.base.utils.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class EnterpriseWeChatService {
    private static final String SEND = "https://qyapi.weixin.qq.com/cgi-bin/webhook/send";

    public void send(String key, String content) {
        String url = SEND + "?key=" + key;
        Map<String, Object> params = new HashMap<>();
        params.put("msgtype", "text");
        Map<String, Object> text = new HashMap<>();
        params.put("text", text);
        text.put("content", content);
        String param = JsonUtil.toJsonString(params);
        HttpResponse httpResponse = HttpRequest.post(url)
                .body(param, "application/json")
                .execute();
        log.info("{}", httpResponse);
    }
}
