package cn.seven.dailypusher.common.base.web;


import cn.seven.dailypusher.common.base.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;

/**
 * 将Controller中的将参数转为对应的BaseEnum
 * @author wtk
 */
@Component
public class IntegerEnumConverter implements ConverterFactory<Integer, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<Integer, T> getConverter(Class<T> targetType) {
        return new StringToEnumConverter<>(targetType);
    }

    public static class StringToEnumConverter<T extends BaseEnum> implements Converter<Integer, T> {
        private final Class<T> targetType;

        public StringToEnumConverter(Class<T> targetType) {
            this.targetType = targetType;
        }

        @Override
        public T convert(Integer source) {
            return BaseEnum.fromCode(targetType, source);
        }
    }
}
