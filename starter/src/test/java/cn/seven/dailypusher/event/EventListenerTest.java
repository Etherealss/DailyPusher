package cn.seven.dailypusher.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author 王腾坤
 * @date 2023/8/2
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class EventListenerTest {

    @EventListener
    public void listen(MyEvent myEvent) {
        log.info("监听到事件：{}", myEvent);
        throw new RuntimeException("测试异常");
    }
}
