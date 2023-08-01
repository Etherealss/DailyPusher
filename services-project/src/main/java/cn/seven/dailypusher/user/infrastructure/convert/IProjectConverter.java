package cn.seven.dailypusher.user.infrastructure.convert;

import cn.seven.dailypusher.common.base.enums.MapperComponentModel;
import cn.seven.dailypusher.user.infrastructure.client.request.ProjectRequest;
import cn.seven.dailypusher.user.infrastructure.client.response.ProjectResponse;
import cn.seven.dailypusher.user.domain.project.ProjectEntity;
import org.mapstruct.Mapper;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
public interface IProjectConverter {
    ProjectEntity toEntity(ProjectRequest projectRequest);
    ProjectResponse toResponse(ProjectEntity entity);
}
