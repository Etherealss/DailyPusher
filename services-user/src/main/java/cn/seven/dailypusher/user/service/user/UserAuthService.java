package cn.seven.dailypusher.user.service.user;

import cn.seven.dailypusher.common.base.crypt.PasswordEncryptor;
import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.BadRequestException;
import cn.seven.dailypusher.common.base.exception.service.NotFoundException;
import cn.seven.dailypusher.common.security.token.user.UserTokenCertificate;
import cn.seven.dailypusher.common.security.token.user.UserTokenService;
import cn.seven.dailypusher.user.infrastructure.pojo.entity.UserEntity;
import cn.seven.dailypusher.user.infrastructure.pojo.request.UserLoginRequest;
import cn.seven.dailypusher.user.mapper.UserMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author wtk
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class UserAuthService extends ServiceImpl<UserMapper, UserEntity> {
    private final PasswordEncryptor passwordEncryptor;
    private final UserTokenService tokenService;

    /**
     * 用户登录
     * @param request
     * @return token信息
     */
    public UserTokenCertificate login(UserLoginRequest request) {
        // 查询用户信息
        QueryWrapper<UserEntity> query = new QueryWrapper<>();
        query.eq("username", request.getUsername());
        UserEntity user = this.baseMapper.selectOne(query);
        if (user == null) {
            throw new NotFoundException("用户不存在");
        }
        // 验证密码
        if (!passwordEncryptor.match(request.getPassword(), user.getPassword())) {
            throw new BadRequestException(ResultCode.PASSWORD_NOT_MATCH);
        }
        // 生成Token并保存
        UserTokenCertificate tokenCertificate = new UserTokenCertificate(
                user.getId(),
                user.getUsername(),
                user.getRoles()
        );
        tokenService.completeTokenAndSave(tokenCertificate);
        return tokenCertificate;
    }

    /**
     * 验证是否已登录（通过token）
     * @param token
     * @return
     */
    public boolean checkLogin(String token) {
        // 检查token
        UserTokenCertificate tokenCertificate = tokenService.getToken(token);
        return tokenCertificate != null;
    }

    /**
     * 退出登录
     * @param token
     */
    public void logout(String token) {
        // 使Token失效
        tokenService.invalidateToken(token);
    }
}
