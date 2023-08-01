package cn.seven.dailypusher.daily.infrastructure.event;

import cn.seven.dailypusher.daily.domain.content.ContentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class PushContentListener {
    private final ContentService contentService;

    @EventListener(PushContentEvent.class)
    public void pushContent(PushContentEvent event) {
        contentService.pushContent(event.getContentId());
    }
}
