package cn.seven.dailypusher.user.infrastructure.converter;

import cn.seven.dailypusher.common.base.enums.MapperComponentModel;
import cn.seven.dailypusher.user.infrastructure.pojo.dto.UserBriefDTO;
import cn.seven.dailypusher.user.infrastructure.pojo.entity.UserEntity;
import cn.seven.dailypusher.user.infrastructure.pojo.request.CreateUserRequest;
import cn.seven.dailypusher.user.infrastructure.pojo.request.UpdateUserInfoRequest;
import org.mapstruct.Mapper;

/**
 * @author wtk
 */
@Mapper(componentModel = MapperComponentModel.SPRING)
public interface UserConverter {
    UserBriefDTO toBriefDTO(UserEntity user);
    UserEntity toEntity(CreateUserRequest request);
    UserEntity toEntity(UpdateUserInfoRequest request);
}
