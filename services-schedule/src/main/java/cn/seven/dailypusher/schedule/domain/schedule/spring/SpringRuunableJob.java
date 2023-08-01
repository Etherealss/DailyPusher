package cn.seven.dailypusher.schedule.domain.schedule.spring;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Slf4j
@RequiredArgsConstructor
public class SpringRuunableJob implements Runnable {
    private final SpringJobCallback springJobCallback;

    @Override
    public void run() {
        log.info("SpringRuunableJob 定时任务到期执行");
        springJobCallback.execute();
    }
}
