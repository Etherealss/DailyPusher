package cn.seven.dailypusher.daily.domain.briefing;

import cn.seven.dailypusher.common.base.exception.service.NotFoundException;
import cn.seven.dailypusher.common.base.pojo.dto.PageDTO;
import cn.seven.dailypusher.daily.infrastructure.client.response.BriefingResponse;
import cn.seven.dailypusher.daily.infrastructure.converter.BriefingConverter;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 简报
 * @author 王腾坤
 * @date 2023/7/28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BriefingService extends ServiceImpl<BriefingRepository, BriefingEntity> {
    private final BriefingConverter briefingConverter;

    public BriefingResponse getById(Long id) {
        BriefingEntity briefingEntity = this.lambdaQuery()
                .eq(BriefingEntity::getId, id)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(BriefingEntity.class, id.toString()));
        return briefingConverter.toResponse(briefingEntity);
    }

    public PageDTO<BriefingResponse> page(int currentPage, int size) {
        Page<BriefingEntity> page = lambdaQuery()
                .page(new Page<>(currentPage, size));
        List<BriefingResponse> collect = page.getRecords().stream()
                .map(briefingConverter::toResponse)
                .collect(Collectors.toList());
        return new PageDTO<>(collect, page);
    }
}
