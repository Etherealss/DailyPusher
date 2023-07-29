package cn.seven.dailypusher.daily.controller;

import cn.seven.dailypusher.common.base.web.ResponseAdvice;
import cn.seven.dailypusher.daily.domain.content.ContentService;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentScheduleRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RestController
@RequestMapping("/contents/{contentId}/jobs")
@RequiredArgsConstructor
@ResponseAdvice
public class ContentScheduleController {

    private final ContentService contentService;

    @PostMapping
    public void createContentSchedule(@PathVariable("contentId") Long contentId,
                                      @RequestBody @Validated ContentScheduleRequest param) {
        contentService.createJob(contentId, param);
    }

    @PutMapping
    public void updateJob(@PathVariable("contentId") Long contentId,
                          @RequestBody @Validated ContentScheduleRequest params) {
        contentService.updateJob(contentId, params);
    }

    @DeleteMapping
    public void deleteJob(@PathVariable("contentId") Long contentId) {
        contentService.deleteJob(contentId);
    }

    @PutMapping("/actions/run")
    public void runJob(@PathVariable("contentId") Long contentId) {
        contentService.runJob(contentId);
    }

    @PutMapping("/actions/stop")
    public void stopJob(@PathVariable("contentId") Long contentId) {
        contentService.stopJob(contentId);
    }
}
