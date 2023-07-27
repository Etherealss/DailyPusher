package cn.seven.dailypusher.common.cron.config.feign;


import cn.seven.dailypusher.common.base.exception.service.ServiceFiegnException;
import cn.seven.dailypusher.common.base.utils.JsonUtil;
import com.xxl.job.core.biz.model.ReturnT;
import feign.Response;
import feign.Util;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @author wtk
 * @date 2022-09-02
 */
@Slf4j
public class XxlFeignResultDecoder implements Decoder {

    @Override
    public Object decode(Response response, Type type) throws IOException {
        if (response.body() == null) {
            throw new DecodeException(response.status(), "没有返回有效的数据", response.request());
        }
        if (type == Response.class) {
            return response;
        }
        //对结果进行转换
        String bodyStr = Util.toString(response.body().asReader(Util.UTF_8));
        ReturnT<?> result;
        try {
            result = JsonUtil.toObject(bodyStr, ReturnT.class);
        } catch (Exception e) {
            throw new DecodeException(
                    response.status(),
                    "没有返回预期的数据，响应体无法转为 Msg.class。相应数据：" + bodyStr,
                    response.request()
            );
        }
        //如果返回错误，则直接抛出异常
        if (ReturnT.SUCCESS_CODE != result.getCode()) {
            throw new ServiceFiegnException(result.getMsg(), response.status(), response.request());
        }
        try {
            return JsonUtil.toGenericObject(JsonUtil.toJsonString(result.getContent()), type);
        } catch (Exception e) {
            throw new DecodeException(
                    response.status(),
                    "没有返回预期的数据，响应体无法转为 " + type.getTypeName() + "。相应数据：" + bodyStr,
                    response.request()
            );
        }
    }
}
