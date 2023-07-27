package cn.seven.dailypusher.common.cron.remote;

import cn.seven.dailypusher.common.cron.config.XxlFeignConfig;
import cn.seven.dailypusher.common.cron.entity.XxlJobInfo;
import cn.seven.dailypusher.common.cron.entity.XxlJobLoginParam;
import cn.seven.dailypusher.common.cron.entity.XxlJobTaskIdParam;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author 王腾坤
 * @date 2023/7/27
 */
@Component
@FeignClient(name = "xxl-job", url = "${xxl.job.admin.addresses}", configuration = XxlFeignConfig.class)
public interface XxlJobFeign {

    @PostMapping(value = "/login", consumes = {"application/x-www-form-urlencoded"})
    Response login(@RequestBody XxlJobLoginParam params);

    @PostMapping("/jobinfo/add")
    String insertJob(@RequestBody XxlJobInfo params, @RequestHeader("Cookie") String cookie);

    @PostMapping("/jobinfo/update")
    void updateJob(@RequestBody XxlJobInfo params, @RequestHeader("Cookie") String cookie);

    @PostMapping("/jobinfo/remove")
    void deleteJob(@RequestBody XxlJobTaskIdParam idParam, @RequestHeader("Cookie") String cookie);

    @PostMapping("/jobinfo/start")
    void runJob(@RequestBody XxlJobTaskIdParam idParam, @RequestHeader("Cookie") String cookie);

    @PostMapping("/jobinfo/stop")
    void stopJob(@RequestBody XxlJobTaskIdParam idParam, @RequestHeader("Cookie") String cookie);
}
