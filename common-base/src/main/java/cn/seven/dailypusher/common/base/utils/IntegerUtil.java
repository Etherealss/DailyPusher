package cn.seven.dailypusher.common.base.utils;

/**
 * @author wtk
 */
public class IntegerUtil {
    public static int long2Int(long num) {
        return num < Integer.MAX_VALUE ? (int) num : Integer.MAX_VALUE;
    }
}
