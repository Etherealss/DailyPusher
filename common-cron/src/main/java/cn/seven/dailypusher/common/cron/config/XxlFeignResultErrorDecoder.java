package cn.seven.dailypusher.common.cron.config;


import cn.seven.dailypusher.common.base.exception.service.ServiceFiegnException;
import cn.seven.dailypusher.common.base.utils.JsonUtil;
import com.xxl.job.core.biz.model.ReturnT;
import feign.Response;
import feign.Util;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;

/**
 * @author wtk
 * @date 2022-10-08
 */
@RequiredArgsConstructor
public class XxlFeignResultErrorDecoder implements ErrorDecoder {

    private final Default defaultDecoder;

    @Override
    public Exception decode(String methodKey, Response response) {
        if (response.body() == null) {
            return defaultDecoder.decode(methodKey, response);
        }
        try {
            String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
            ReturnT<?> result = JsonUtil.toObject(bodyStr, ReturnT.class);
            return new ServiceFiegnException(result.getMsg(), response.status(), response.request());
        } catch (Exception ignored) {
            return defaultDecoder.decode(methodKey, response);
        }
    }
}
