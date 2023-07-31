package cn.seven.dailypusher.daily.controller;

import cn.seven.dailypusher.common.base.pojo.dto.PageDTO;
import cn.seven.dailypusher.common.base.web.ResponseAdvice;
import cn.seven.dailypusher.daily.domain.content.ContentService;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentRequest;
import cn.seven.dailypusher.daily.infrastructure.client.response.ContentResponse;
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
public class ContentController {

    private final ContentService contentService;

    @PostMapping("/contents")
    public Long create(@RequestBody @Validated ContentRequest contentRequest) {
        return contentService.create(contentRequest);
    }

    @PutMapping("/contents/{id}")
    public void update(@PathVariable Long id,
                       @RequestBody @Validated ContentRequest contentRequest) {
        contentService.update(id, contentRequest);
    }

    @DeleteMapping("/contents/{id}")
    public void delete(@PathVariable Long id) {
        contentService.delete(id);
    }

    @GetMapping("/contents/{id}")
    public ContentResponse get(@PathVariable Long id) {
        return contentService.getById(id);
    }

    @GetMapping("/pages/contents")
    public PageDTO<ContentResponse> page(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "current", defaultValue = "1") int currentPage) {
        return contentService.page(currentPage, size);
    }
}
