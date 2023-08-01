package cn.seven.dailypush.push.domain.handler;

import cn.seven.dailypush.push.infrastructure.client.request.PushRequest;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
public interface IPushHandler {

    /**
     * 推送
     */
    void push(PushRequest pushRequest);
}
