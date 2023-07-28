package cn.seven.dailypusher.common.cron.remote;

import cn.seven.dailypusher.common.cron.config.feign.XxlFeignConfig;
import cn.seven.dailypusher.common.cron.remote.param.XxlJobInfo;
import cn.seven.dailypusher.common.cron.remote.param.XxlJobLoginParam;
import cn.seven.dailypusher.common.cron.remote.param.XxlJobTaskIdParam;
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

    @PostMapping(value = "/jobinfo/add", consumes = {"application/x-www-form-urlencoded"})
    Integer insertJob(@RequestBody XxlJobInfo params, @RequestHeader("Cookie") String cookie);

    @PostMapping(value = "/jobinfo/update", consumes = {"application/x-www-form-urlencoded"})
    void updateJob(@RequestBody XxlJobInfo params, @RequestHeader("Cookie") String cookie);

    @PostMapping(value = "/jobinfo/remove", consumes = {"application/x-www-form-urlencoded"})
    void deleteJob(@RequestBody XxlJobTaskIdParam idParam, @RequestHeader("Cookie") String cookie);

    @PostMapping(value = "/jobinfo/start", consumes = {"application/x-www-form-urlencoded"})
    void runJob(@RequestBody XxlJobTaskIdParam idParam, @RequestHeader("Cookie") String cookie);

    @PostMapping(value = "/jobinfo/stop", consumes = {"application/x-www-form-urlencoded"})
    void stopJob(@RequestBody XxlJobTaskIdParam idParam, @RequestHeader("Cookie") String cookie);
}
