package cn.seven.dailypusher.common.base.feign;


import cn.seven.dailypusher.common.base.exception.service.ServiceFiegnException;
import cn.seven.dailypusher.common.base.pojo.dto.Result;
import cn.seven.dailypusher.common.base.utils.JsonUtil;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;

/**
 * @author wtk
 * @date 2022-10-08
 */
@RequiredArgsConstructor
public class FeignResultErrorDecoder implements ErrorDecoder {

    private final Default defaultDecoder;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.body() == null) {
            return defaultDecoder.decode(methodKey, response);
        }
        try {
            //对结果进行转换
            String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
            // hutool 会将空字段填充 JSONNull 对象，会与 Spring 默认的 jackson 冲突。导致异常
            // 解决办法是不用 Hutool，改用 jackson
            Result<?> result = JsonUtil.toObject(bodyStr, Result.class);
            if (result.getCode() == 0) {
                // bodyStr 读取出来的非 Msg 对象
                return defaultDecoder.decode(methodKey, response);
            }
            if (result.getData() == null) {
                result.setData(null);
            }
            return new ServiceFiegnException(result.getDesc(), response.status(), response.request());
        } catch (Exception ignored) {
            return defaultDecoder.decode(methodKey, response);
        }
    }
}
