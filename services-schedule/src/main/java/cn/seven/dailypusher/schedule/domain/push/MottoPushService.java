package cn.seven.dailypusher.schedule.domain.push;

import cn.seven.dailypush.push.domain.PushService;
import cn.seven.dailypusher.motto.domain.service.MottoService;
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
public class MottoPushService {
    private final MottoService mottoService;
    private final PushService pushService;

    public void push() {
        pushService.push(mottoService.getMotto().getContent());
    }
}
