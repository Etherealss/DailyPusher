package cn.seven.dailypusher.daily.controller;

import cn.seven.dailypusher.common.base.pojo.dto.PageDTO;
import cn.seven.dailypusher.common.base.web.ResponseAdvice;
import cn.seven.dailypusher.daily.domain.briefing.BriefingEntity;
import cn.seven.dailypusher.daily.domain.briefing.BriefingService;
import cn.seven.dailypusher.daily.infrastructure.client.request.BriefingRequest;
import cn.seven.dailypusher.daily.infrastructure.client.response.BriefingResponse;
import cn.seven.dailypusher.daily.infrastructure.converter.BriefingConverter;
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
@RequestMapping
@RequiredArgsConstructor
@ResponseAdvice
public class BriefingController {

    private final BriefingService briefingService;
    private final BriefingConverter briefingConverter;

    @PostMapping("/briefings")
    public Long addBriefing(@RequestBody @Validated BriefingRequest briefingRequest) {
        BriefingEntity entity = briefingConverter.toEntity(briefingRequest);
        briefingService.save(entity);
        return entity.getId();
    }

    @PutMapping("/briefings/{id}")
    public void updateBriefing(@PathVariable Long id,
                               @RequestBody @Validated BriefingRequest briefingRequest) {
        briefingService.updateById(briefingConverter.toEntity(briefingRequest));
    }

    @DeleteMapping("/briefings/{id}")
    public void deleteBriefing(@PathVariable Long id) {
        briefingService.removeById(id);
    }

    @GetMapping("/briefings/{id}")
    public BriefingResponse getBriefing(@PathVariable Long id) {
        return briefingConverter.toResponse(briefingService.getById(id));
    }

    @GetMapping("/pages/briefings")
    public PageDTO<BriefingResponse> pageReviewImg(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "current", defaultValue = "1") int currentPage) {
        return briefingService.page(currentPage, size);
    }
}
