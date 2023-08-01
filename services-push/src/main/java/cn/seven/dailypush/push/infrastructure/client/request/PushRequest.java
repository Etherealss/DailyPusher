package cn.seven.dailypush.push.infrastructure.client.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@Data
@Accessors(chain = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PushRequest {
    List<String> targetKeys;
    String content;
}
