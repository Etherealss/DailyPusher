package cn.seven.dailypusher.daily.infrastructure.client.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BriefingRequest {
    /**
     * 内容
     */
    @NotBlank
    String content;

    /**
     * 作者姓名
     */
    @NotBlank
    String author;
}
