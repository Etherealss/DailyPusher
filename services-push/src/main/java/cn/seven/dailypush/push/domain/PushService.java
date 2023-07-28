package cn.seven.dailypush.push.domain;

import cn.seven.dailypush.push.domain.handler.IPushHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PushService {
    private final IPushHandler pushHandler;

    public void push(String content) {
        pushHandler.push(content);
    }
}
