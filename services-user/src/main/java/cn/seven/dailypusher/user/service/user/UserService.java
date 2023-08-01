package cn.seven.dailypusher.user.service.user;

import cn.seven.dailypusher.common.base.crypt.PasswordEncryptor;
import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.BadRequestException;
import cn.seven.dailypusher.common.base.exception.service.ExistException;
import cn.seven.dailypusher.common.base.exception.service.NotFoundException;
import cn.seven.dailypusher.user.infrastructure.converter.UserConverter;
import cn.seven.dailypusher.user.infrastructure.pojo.dto.UserBriefDTO;
import cn.seven.dailypusher.user.infrastructure.pojo.entity.UserEntity;
import cn.seven.dailypusher.user.infrastructure.pojo.request.CreateUserRequest;
import cn.seven.dailypusher.user.infrastructure.pojo.request.UpdateUserInfoRequest;
import cn.seven.dailypusher.user.mapper.UserMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wtk
 */
@Service
@Slf4j
@AllArgsConstructor
public class UserService extends ServiceImpl<UserMapper, UserEntity> {
    private final UserMapper userMapper;
    private final UserConverter userConverter;
    private final PasswordEncryptor passwordEncryptor;

    /**
     * 注册用户
     * @param request
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long createUser(CreateUserRequest request) {
        // 用户名是否重复
        if (this.checkUsernameExists(request.getUsername())) {
            throw new ExistException(UserEntity.class, request.getUsername());
        }
        UserEntity userEntity = userConverter.toEntity(request);
        // 密码加密
        userEntity.setPassword(passwordEncryptor.encode(userEntity.getPassword()));
        this.save(userEntity);
        return userEntity.getId();
    }

    /**
     * 获取用户基本信息
     * @param userId
     * @return
     */
    public UserBriefDTO getBriefById(Long userId) {
        UserEntity user = lambdaQuery()
                .eq(UserEntity::getId, userId)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(UserEntity.class, userId.toString()));
        return userConverter.toBriefDTO(user);
    }

    /**
     * 批量获取用户信息
     * @param userIds
     * @return
     */
    public List<UserBriefDTO> getBatchBriefsByIds(Collection<Long> userIds) {
        List<UserEntity> users = userMapper.selectBatchIds(userIds);
        return users.stream()
                .map(userConverter::toBriefDTO)
                .collect(Collectors.toList());
    }

    /**
     * 用户名是否重复
     * @param username
     * @return
     */
    public boolean checkUsernameExists(String username) {
        return lambdaQuery().eq(UserEntity::getUsername, username).count() > 0;
    }

    /**
     * 更新用户信息
     * @param userId
     * @param req
     */
    @Transactional(rollbackFor = Exception.class)
    public void update(Long userId, UpdateUserInfoRequest req) {
        UserEntity user = lambdaQuery()
                .eq(UserEntity::getId, userId)
                .oneOpt()
                .orElseThrow(() -> new NotFoundException(UserEntity.class, userId.toString()));
        String newPassword = req.getNewPassword();
        if (!passwordEncryptor.match(req.getCurPassword(), user.getPassword())) {
            throw new BadRequestException(ResultCode.PASSWORD_NOT_MATCH);
        }
        UserEntity update = userConverter.toEntity(req);
        update.setPassword(newPassword);
        update.setId(userId);
        this.saveOrUpdate(update);
    }
}
