package cn.seven.dailypusher.daily.infrastructure.converter;

import cn.seven.dailypusher.common.base.enums.MapperComponentModel;
import cn.seven.dailypusher.daily.domain.content.ContentEntity;
import cn.seven.dailypusher.daily.infrastructure.client.request.ContentRequest;
import cn.seven.dailypusher.daily.infrastructure.client.response.ContentResponse;
import org.mapstruct.Mapper;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Mapper(componentModel = MapperComponentModel.SPRING)
public interface ContentConverter {
    ContentEntity toEntity(ContentRequest contentRequest);
    ContentResponse toResponse(ContentEntity entity);
}
