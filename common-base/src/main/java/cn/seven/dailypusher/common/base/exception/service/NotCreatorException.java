package cn.seven.dailypusher.common.base.exception.service;


import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.BadRequestException;

/**
 * @author wtk
 */
public class NotCreatorException extends BadRequestException {
    public NotCreatorException() {
        super(ResultCode.NOT_AUTHOR);
    }

    public NotCreatorException(String message) {
        super(ResultCode.NOT_AUTHOR, message);
    }

    public NotCreatorException(Long userId, Long targetId) {
        super(ResultCode.NOT_AUTHOR, "用户'" + userId + "'不是'" + targetId + "'的创建者");
    }
}
