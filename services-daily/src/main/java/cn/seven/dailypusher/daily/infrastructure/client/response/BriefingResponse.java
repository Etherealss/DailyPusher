package cn.seven.dailypusher.daily.infrastructure.client.response;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Date;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BriefingResponse {
    Long id;
    String author;
    String content;
    Date createTime;
}
