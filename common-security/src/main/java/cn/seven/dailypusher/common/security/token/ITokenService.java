package cn.seven.dailypusher.common.security.token;


import cn.seven.dailypusher.common.security.auth.exception.TokenException;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

/**
 * @author wtk
 */
public interface ITokenService<T extends TokenCertificate> {

    void completeTokenAndSave(@NonNull TokenCertificate credential);

    @NonNull
    T assertToken(String token) throws TokenException;

    /**
     * 使 token 失效
     * @param token
     */
    void invalidateToken(String token);

    /**
     * 获取token
     * @param token
     * @return 不存在时返回null
     */
    @Nullable
    T getToken(String token);
}
