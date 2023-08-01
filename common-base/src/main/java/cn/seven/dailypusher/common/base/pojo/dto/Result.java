package cn.seven.dailypusher.common.base.pojo.dto;

import cn.seven.dailypusher.common.base.enums.ResultCode;
import cn.seven.dailypusher.common.base.exception.BaseException;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.util.StringUtils;

import java.io.Serializable;

/**
 * 统一的接口信息包，用于前后端交互
 * @author wtk
 */
@Getter
@Setter
@ToString
public class Result<T> implements Serializable {
    /** 响应是否成功 */
    private boolean success;
    /** 结果码，用于判断响应情况 */
    private int code;

    /** 响应情况说明，如“用户密码错误” */
    private String desc;

    /** 响应的数据，可能为空 */
    private T data;

    public static <T> Result<T> ok() {
        return new Result<>(true, ResultCode.OK);
    }

    public static <T> Result<T> ok(T data) {
        Result<T> result = new Result<>(true, ResultCode.OK);
        result.setData(data);
        return result;
    }
    public static <T> Result<T> ok(String message) {
        return new Result<>(true, ResultCode.OK, message);
    }

    public static <T> Result<T> exception(Throwable e) {
        return new Result<>(false, ResultCode.SERVER_ERROR, e.getMessage());
    }

    /**
     * 默认访问成功 OK
     */
    public Result() {
        this(true, ResultCode.OK);
    }

    public Result(boolean success, ResultCode resultCode) {
        this.success = success;
        this.code = resultCode.getCode();
        this.desc = resultCode.getMessage();
    }


    public Result(boolean success, ResultCode resultCode, String description) {
        this.success = success;
        this.code = resultCode.getCode();
        this.desc = resultCode.getMessage() + " " + description;
    }
    /**
     * 用自定义的异常生成ApiMsg
     * 自定义异常的message包含了ResultCode的说明和自定义描述
     * @param e
     */
    public Result(BaseException e) {
        this.success = false;
        this.code = e.getCode();
        this.desc = e.getMessage();
    }

    /**
     * 使用非自定义的其他异常生成ApiMsg
     * 手动拼接ResultCode的说明和自定义描述
     * @param throwable
     */
    public Result(Throwable throwable) {
        this.code = ResultCode.SERVER_ERROR.getCode();
        String message = ResultCode.SERVER_ERROR.getMessage();
        if (StringUtils.hasText(throwable.getMessage())) {
            message += (": " + throwable.getMessage());
        }
        this.desc = message;
    }
}
