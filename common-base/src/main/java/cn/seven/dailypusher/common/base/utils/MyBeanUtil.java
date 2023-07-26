package cn.seven.dailypusher.common.base.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * @author wtk
 */
public class MyBeanUtil extends BeanUtils {

    @Nullable
    public static <T> T transformFrom(@Nullable Object source, @NonNull Class<T> targetClass) {
        Assert.notNull(targetClass, "目标对象不能为null");

        if (source == null) {
            return null;
        }
        T t = ReflectUtil.newInstance(targetClass);
        BeanUtils.copyProperties(source, t);
        return t;
    }

}
