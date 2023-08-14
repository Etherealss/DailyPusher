package cn.seven.dailypush.push.domain.handler;

import cn.seven.dailypush.push.infrastructure.client.request.PushRequest;
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

    @Override
    public void push(PushRequest pushRequest) {
        for (String targetKey : pushRequest.getTargetKeys()) {
            enterpriseWeChatService.send(
                    targetKey,
                    pushRequest.getContent(),
                    pushRequest.getMentionedMobiles()
            );
        }
    }
}
