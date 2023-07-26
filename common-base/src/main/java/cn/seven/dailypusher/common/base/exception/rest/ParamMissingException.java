package cn.seven.dailypusher.common.base.exception.rest;


import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.BadRequestException;

/**
 * 参数缺失
 * @author wtk
 */
public class ParamMissingException extends BadRequestException {
    public ParamMissingException() {
        super(ResultCode.MISSING_PARAM);
    }

    public ParamMissingException(String paramName) {
        super(ResultCode.MISSING_PARAM, paramName + "参数缺失");
    }

    public ParamMissingException(String paramName, String desc) {
        super(ResultCode.MISSING_PARAM, paramName + "参数缺失，" + desc);
    }
}
