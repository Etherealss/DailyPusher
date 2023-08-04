package cn.seven.dailypusher.daily.domain.content;

import cn.seven.dailypusher.common.base.pojo.entity.IdentifiedEntity;
import cn.seven.dailypusher.common.database.repository.List2JsonTypeHandler;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@TableName("content")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class ContentEntity extends IdentifiedEntity {

    /**
     * 内容名称
     */
    @TableField(value = "content_name")
    String contentName;

    /**
     * 项目ID
     */
    @TableField(value = "project_id")
    Long projectId;

    /**
     * 简报
     */
    @TableField(value = "briefing")
    String briefing;

    /**
     * 是否包含天气
     */
    @TableField(value = "contain_weather")
    Boolean containWeather;

    @TableField(value = "city_for_weather")
    String cityForWeather;

    /**
     * 是否包含格言
     */
    @TableField(value = "contain_motto")
    Boolean containMotto;

    /**
     * 企微webhook key
     */
    @TableField(value = "enterprise_we_chat_hook_keys", typeHandler = List2JsonTypeHandler.class)
    List<String> enterpriseWeChatHookKeys;
}
