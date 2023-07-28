package cn.seven.dailypusher.schedule.controller;

import cn.seven.dailypusher.common.base.web.ResponseAdvice;
import cn.seven.dailypusher.schedule.domain.schedule.ScheduledJobService;
import cn.seven.dailypusher.schedule.infrastructure.client.request.ScheduleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
@ResponseAdvice
public class ScheduledJobController {

    private final ScheduledJobService scheduledJobService;

    @PostMapping("/{jobId}")
    public Integer createJob(@RequestBody @Validated ScheduleRequest param) {
        return scheduledJobService.createJob(param);
    }


    @PutMapping("/{jobId}")
    public void updateJob(@PathVariable("jobId") Integer jobId,
                          @RequestBody @Validated ScheduleRequest params) {
        scheduledJobService.updateJob(jobId, params);
    }

    @DeleteMapping("/{jobId}")
    public void deleteJob(@PathVariable("jobId") Integer jobId) {
        scheduledJobService.deleteJob(jobId);
    }

    @PostMapping("/{jobId}/actions/run")
    public void runJob(@PathVariable("jobId") Integer jobId) {
        scheduledJobService.runJob(jobId);
    }

    @DeleteMapping("/{jobId}/actions/stop")
    public void stopJob(@PathVariable("jobId") Integer jobId) {
        scheduledJobService.stopJob(jobId);
    }
}
