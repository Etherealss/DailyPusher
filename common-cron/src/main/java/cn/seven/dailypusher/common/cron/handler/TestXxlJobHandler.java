package cn.seven.dailypusher.common.cron.handler;

import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author 王腾坤
 * @date 2023/7/27
 */
@Slf4j
@Component
public class TestXxlJobHandler extends IJobHandler {

    @Override
    @XxlJob(value = "sampleXxlJobHandler", init = "init", destroy = "destroy")
    public void execute() throws Exception {
        log.info("自动任务" + this.getClass().getSimpleName() + "执行");
    }

    public void init(){
        log.info("init");
    }
    public void destroy(){
        log.info("destroy");
    }
}
