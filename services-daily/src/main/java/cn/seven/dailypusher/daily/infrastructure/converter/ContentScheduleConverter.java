package cn.seven.dailypusher.daily.infrastructure.converter;

import cn.seven.dailypusher.common.base.enums.MapperComponentModel;
import cn.seven.dailypusher.daily.domain.content.schedule.ContentScheduleEntity;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentScheduleRequest;
import cn.seven.dailypusher.daily.infrastructure.client.response.ContentScheduleResponse;
import org.mapstruct.Mapper;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Mapper(componentModel = MapperComponentModel.SPRING)
public interface ContentScheduleConverter {
    ContentScheduleEntity toEntity(ContentScheduleRequest request);
    ContentScheduleResponse toResponse(ContentScheduleEntity entity);
}
