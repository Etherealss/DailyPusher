package cn.seven.dailypush.push.domain.handler;

import cn.seven.dailypush.push.infrastructure.config.EnterpriseWeChatConfig;
import cn.seven.dailypush.push.infrastructure.remote.EnterpriseWeChatService;
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
public class EnterpriseWeChatPushHandler implements IPushHandler {
    private final EnterpriseWeChatService enterpriseWeChatService;
    private final EnterpriseWeChatConfig enterpriseWeChatConfig;

    @Override
    public void push(String content) {
        enterpriseWeChatService.send(enterpriseWeChatConfig.getWebhookKey(), content);
    }
}
