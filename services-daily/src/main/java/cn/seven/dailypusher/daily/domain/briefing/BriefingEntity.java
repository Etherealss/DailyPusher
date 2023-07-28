package cn.seven.dailypusher.daily.domain.briefing;

import cn.seven.dailypusher.common.base.pojo.entity.IdentifiedEntity;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
@TableName("briefing")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BriefingEntity extends IdentifiedEntity {
    /**
     * 内容
     */
    String content;

    /**
     * 作者
     */
    String author;
}
