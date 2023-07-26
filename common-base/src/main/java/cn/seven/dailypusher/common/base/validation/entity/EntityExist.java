package cn.seven.dailypusher.common.base.validation.entity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wtk
 */
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EntityExist {
    /**
     * 设置 EntityValidateHandler 的 BeanName
     * @return
     */
    String[] value() default {};
}