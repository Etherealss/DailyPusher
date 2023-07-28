package cn.seven.dailypusher.daily.infrastructure.converter;

import cn.seven.dailypusher.common.base.enums.MapperComponentModel;
import cn.seven.dailypusher.daily.domain.briefing.BriefingEntity;
import cn.seven.dailypusher.daily.infrastructure.client.request.BriefingRequest;
import cn.seven.dailypusher.daily.infrastructure.client.response.BriefingResponse;
import org.mapstruct.Mapper;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Mapper(componentModel = MapperComponentModel.SPRING)
public interface BriefingConverter {
    BriefingEntity toEntity(BriefingRequest briefingRequest);
    BriefingResponse toResponse(BriefingEntity entity);
}
