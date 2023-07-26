package cn.seven.dailypusher.common.base.utils;

import javax.validation.ConstraintValidatorContext;

/**
 * @author wtk
 */
public class ValidationUtil {

    public static void customMessage(ConstraintValidatorContext ctx, String msg) {
        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
    }
}
