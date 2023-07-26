package cn.seven.dailypusher.common.security.auth.exception;


import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.service.AuthenticationException;

/**
 * @author wtk
 */
public class AgeLimitedException extends AuthenticationException {
    public AgeLimitedException(String message) {
        super(ResultCode.AGE_NOT_ALLOW, message);
    }

    public AgeLimitedException() {
        super(ResultCode.AGE_NOT_ALLOW);
    }
}
