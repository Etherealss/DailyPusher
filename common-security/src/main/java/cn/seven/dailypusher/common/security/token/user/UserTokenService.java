package cn.seven.dailypusher.common.security.token.user;

import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.security.auth.exception.TokenException;
import cn.seven.dailypusher.common.security.config.UserCertificateConfig;
import cn.seven.dailypusher.common.security.token.RedisTokenService;
import cn.seven.dailypusher.common.security.token.TokenCertificate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @author wtk
 */
@SuppressWarnings("unchecked")
@Component
@Slf4j
public class UserTokenService extends RedisTokenService<UserTokenCertificate> {

    public UserTokenService(UserCertificateConfig config, RedisTemplate<String, TokenCertificate> redisTemplate) {
        super(config, redisTemplate);
    }

    @Override
    public UserTokenCertificate assertToken(String token) throws TokenException {
        TokenCertificate certificate = redisTemplate.opsForValue().get(tokenKey(token));
        if (certificate == null) {
            throw new TokenException(ResultCode.USER_TOKEN_INVALID);
        }
        return (UserTokenCertificate) certificate;
    }

    @Override
    public UserTokenCertificate getToken(String token) {
        TokenCertificate certificate = redisTemplate.opsForValue().get(tokenKey(token));
        if (certificate == null) {
            return null;
        }
        return (UserTokenCertificate) certificate;
    }
}
