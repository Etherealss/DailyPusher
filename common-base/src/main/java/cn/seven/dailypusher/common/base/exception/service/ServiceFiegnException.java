package cn.seven.dailypusher.common.base.exception.service;


import feign.Request;
import feign.codec.DecodeException;
import lombok.Getter;
import lombok.ToString;

/**
 * @author wtk
 * @date 2022-09-02
 */
@Getter
@ToString
public class ServiceFiegnException extends DecodeException {
    public ServiceFiegnException(String desc, int status, Request request) {
        super(status, desc, request);
    }
}
