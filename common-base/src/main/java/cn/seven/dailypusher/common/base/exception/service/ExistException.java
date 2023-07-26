package cn.seven.dailypusher.common.base.exception.service;


import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.BadRequestException;

/**
 * 目标已存在
 * @author wtk
 */
public class ExistException extends BadRequestException {
    public ExistException(Class<?> clazz) {
        super(ResultCode.EXIST, "对应的" + clazz.getSimpleName() + "已存在");
    }

    public ExistException(String message) {
        super(ResultCode.EXIST, message);
    }

    public ExistException(Class<?> clazz, String identification) {
        super(ResultCode.EXIST, "'" + identification + "'对应的" + clazz.getSimpleName() + "不存在");
    }
}
