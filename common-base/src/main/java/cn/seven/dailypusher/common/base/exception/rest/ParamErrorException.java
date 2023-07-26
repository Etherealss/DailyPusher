package cn.seven.dailypusher.common.base.exception.rest;


import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.BadRequestException;

/**
 * 参数错误
 * @author wtk
 * @description
 
 */
public class ParamErrorException extends BadRequestException {
    public ParamErrorException() {
        super(ResultCode.ERROR_PARAM);
    }

    public ParamErrorException(String message) {
        super(ResultCode.ERROR_PARAM, message);
    }
}
