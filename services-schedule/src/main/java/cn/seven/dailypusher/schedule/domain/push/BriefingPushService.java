package cn.seven.dailypusher.schedule.domain.push;

import cn.seven.dailypush.push.domain.PushService;
import cn.seven.dailypusher.daily.domain.briefing.BriefingService;
import cn.seven.dailypusher.daily.infrastructure.client.response.BriefingResponse;
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
public class BriefingPushService {
    private final PushService pushService;
    private final BriefingService briefingService;

    public void push(Long briefingId) {
        BriefingResponse briefing = briefingService.getById(briefingId);
        pushService.push(briefing.getAuthor() + briefing.getContent());
    }
}
