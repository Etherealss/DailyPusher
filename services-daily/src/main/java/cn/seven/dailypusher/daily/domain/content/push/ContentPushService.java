package cn.seven.dailypusher.daily.domain.content.push;

import cn.seven.dailypush.push.domain.PushService;
import cn.seven.dailypush.push.infrastructure.client.request.PushRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 王腾坤
 * @date 2023/7/31
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ContentPushService {
    private final PushService pushService;

    public void push(List<String> enterpriseWeChatHookKeys, String txt) {
        PushRequest pushRequest = new PushRequest().setTargetKeys(enterpriseWeChatHookKeys).setContent(txt);
        pushService.push(pushRequest);
    }
}
