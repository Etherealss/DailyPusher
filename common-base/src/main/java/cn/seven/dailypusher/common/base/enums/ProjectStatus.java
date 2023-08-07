package cn.seven.dailypusher.common.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 王腾坤
 * @date 2023/8/7
 */
@Getter
@AllArgsConstructor
public enum ProjectStatus implements BaseEnum {
    NOT_START(0, "未开始"),
    PROCESSING(1, "进行中"),
    FINISHED(2, "已结束")
    ;
    private final int code;
    private final String name;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getCode() {
        return code;
    }
}
