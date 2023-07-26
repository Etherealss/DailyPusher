package cn.seven.dailypusher.common.base.exception;

import cn.seven.dailypusher.common.base.enums.ResultCode;
import org.springframework.http.HttpStatus;

/**
 * @author wtk
 */
public class BadRequestException extends BaseException {
    public BadRequestException(int code, HttpStatus httpStatus) {
        super(code, httpStatus);
    }

    public BadRequestException(ResultCode resultCode) {
        super(resultCode);
    }

    public BadRequestException(ResultCode resultCode, String message) {
        super(resultCode, message);
    }

    public BadRequestException(ResultCode resultCode, String message, Throwable e) {
        super(resultCode, message, e);
    }
}
