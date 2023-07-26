package cn.seven.dailypusher.common.base.exception.service;


import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.BadRequestException;
import lombok.Getter;

/**
 * @author wtk
 * @description

 */
@Getter
public class NotFoundException extends BadRequestException {

    private final String identification;

    public NotFoundException(String msg) {
        super(ResultCode.NOT_FOUND, msg);
        identification = null;
    }

    public NotFoundException(Class<?> clazz, String identification) {
        super(ResultCode.NOT_FOUND, "'" + identification + "'对应的" + clazz.getSimpleName() + "不存在");
        this.identification = identification;
    }
}
