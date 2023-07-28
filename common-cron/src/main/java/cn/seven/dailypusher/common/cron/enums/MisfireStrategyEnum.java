package cn.seven.dailypusher.common.cron.enums;

/**
 * @author 王腾坤
 * @date 2023/7/28
 */
public enum MisfireStrategyEnum {

    /**
     * do nothing
     */
    DO_NOTHING,

    /**
     * fire once now
     */
    FIRE_ONCE_NOW;

    MisfireStrategyEnum() {
    }
}