package cn.seven.dailypusher.daily.infrastructure.client.request;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContentRequest {
    /**
     * 项目ID
     */
    @NotNull
    Long projectId;

    /**
     * 简报
     */
    @NotBlank
    String briefing;

    /**
     * 是否包含天气
     */
    @NotNull
    Boolean containWeather;

    // TODO 天气城市

    /**
     * 是否包含格言
     */
    @NotNull
    Boolean containMotto;

    /**
     * 企微webhook key
     */
    List<String> enterpriseWeChatHookKeys;
}
