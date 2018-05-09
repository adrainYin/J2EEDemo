package demo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 定义一个空的注解，表示对将测试的方法的声明。
 * 在测试的时候对方法的注解进行判断，如果是该注解则对该方法进行批量测试
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTestUtil {

}
