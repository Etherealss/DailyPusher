package cn.seven.dailypusher.common.base.exception.service;


import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.BaseException;
import org.springframework.http.HttpStatus;

/**
 * 权限异常
 * @author wtk
 */
public class AuthenticationException extends BaseException {
    public AuthenticationException(ResultCode resultCode) {
        super(resultCode);
    }

    public AuthenticationException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public AuthenticationException(ResultCode resultCode, String message, Throwable e) {
        super(resultCode, message, e);
    }

    public AuthenticationException(int code, HttpStatus httpStatus) {
        super(code, httpStatus);
    }
}
