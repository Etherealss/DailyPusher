package cn.seven.dailypusher.common.cron;

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
        System.out.println("自动任务" + this.getClass().getSimpleName() + "执行");
    }

    public void init(){
        System.out.println("init");
    }
    public void destroy(){
        System.out.println("destroy");
    }
}
