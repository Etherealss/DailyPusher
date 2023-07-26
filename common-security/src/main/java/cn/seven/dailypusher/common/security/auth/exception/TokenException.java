package cn.seven.dailypusher.common.security.auth.exception;


import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.service.AuthenticationException;

/**
 * token异常
 * @author wtk
 */
public class TokenException extends AuthenticationException {
    public TokenException(ResultCode resultCode) {
        super(resultCode);
    }

    public TokenException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }
}
