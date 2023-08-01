package cn.seven.dailypusher.daily.infrastructure.event;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 王腾坤
 * @date 2023/8/1
 */
@AllArgsConstructor
@Data
public class PushContentEvent {
    private final Long contentId;
}