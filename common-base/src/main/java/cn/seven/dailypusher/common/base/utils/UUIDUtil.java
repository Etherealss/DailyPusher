package cn.seven.dailypusher.common.base.utils;

import java.util.UUID;

/**
 * @author wtk
 */
public class UUIDUtil {
    public static String getUuid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
