package utils;

import org.apache.commons.beanutils.Converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 定义转换类，对于BreansUtil来说，有时候需要自己定义类型转换器
 * 自定义类型转换器需要继承Convertor接口，覆写convert方法
 */
public class DateConverterUtil implements Converter {
    /**
     * 泛型方法 ， 接收两个参数
     * @param type 需要转换到的类型，此值可以设置为缺省值，用泛型T和具体返回值识别真正的返回转换类型
     * @param value 被转换的类型，接收Object类型数据
     * @param <T> 设置泛型
     * @return 返回真实的类型
     * 定义好了转换方法后，需要在BeansUtil中进行注册才能够使用
     * 注册：ConvertUtils.register(转换器, 目标类型.class);
     *（1）转换器就是自己编写的工具类
     * (2) 目标类型一定要用.class获得类对象
     */
    @Override
    public  <T> T convert(Class<T> type, Object value) {
        String stringDate = (String)value;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = dateFormat.parse(stringDate);
            return (T)date;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
