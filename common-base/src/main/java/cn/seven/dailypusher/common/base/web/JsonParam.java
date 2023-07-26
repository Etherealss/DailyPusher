package cn.seven.dailypusher.common.base.web;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * @author wtk
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface JsonParam {
    @AliasFor("name")
    String value() default "";

    @AliasFor("value")
    String name() default "";

    boolean required() default true;

    String defaultValue() default "";
}
