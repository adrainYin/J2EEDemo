package test;

import java.lang.annotation.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 自定义注解
 * 注解的中只有属性方法，而没有普通方法。属性方法和属性是同一样的东西，只不过在后面加上了括号
 * 属性方法后面可以跟上默认值
 */
@Retention(RetentionPolicy.RUNTIME)
@interface SuppressWarnings{
    String[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.CONSTRUCTOR})
@interface MethodAnnotation{
    String[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
@interface ConStructor{
    int[] value();
}

@MyAnnotation()
public class AnnotationTest {
    @Check(msg = "fail")
    public int states;


    @SuppressWarnings({"压制警告", "哈哈哈我只一个数组而且不用value值"})
    public String msg;

    @MethodAnnotation("110")
    public void fun(){
        System.out.println("我是一个测试demo");
    }

    @MethodAnnotation("001")
    public int intFun(){
        System.out.println("输出一个整数");
        return 1;
    }

    public String intFun(int num){
        System.out.println(num + 1);
        return "输出的值为:" + (num+1);
    }
    @ConStructor(110)
    public AnnotationTest(){
        System.out.println("hahahaahahaha");
    }


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        boolean hasAnnotation = AnnotationTest.class.isAnnotationPresent(MyAnnotation.class);
        System.out.println(hasAnnotation);
        if (hasAnnotation){
            MyAnnotation myAnnotation = AnnotationTest.class.getAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation.username());
            System.out.println(myAnnotation.age());

        }

        try {
            Field field = AnnotationTest.class.getDeclaredField("states");
            Check check = field.getAnnotation(Check.class);
            System.out.println(check.msg());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            Field msgField = AnnotationTest.class.getDeclaredField("msg");
            SuppressWarnings suppressWarnings = msgField.getAnnotation(SuppressWarnings.class);
            System.out.println(suppressWarnings.value()[0] + suppressWarnings.value()[1]);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        try {
            Method method = AnnotationTest.class.getDeclaredMethod("fun");
            MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
            System.out.println(methodAnnotation.value()[0]);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        /**
         * getMethod()的第二个参数是函数参数的类型而不是函数返回值的类型，因为函数存在重载的情况
         */

        //在使用反射取得类的class时需要加上绝对路径，因为类加载器是从根目录下找起
        Class cls = Class.forName("test.AnnotationTest");
        AnnotationTest annotationTest = (AnnotationTest)cls.newInstance();
        Method method = cls.getDeclaredMethod("intFun",null);
        Method method1 = cls.getDeclaredMethod("intFun", int.class);
        /**
         * invoke函数的解释：invoke的返回参数是Object类型，实际返回的就是定义的函数的返回类型
         * 两个参数：(1) 第一个是调用此函数的对象，传入的是具体的Object对象而不是Class类对象
         * 当调用静态方法时可以不需要实例对象，此时该参数可以设置为null
         * (2)函数的参数，这里也是具体的Object对象
         */
        method.invoke(annotationTest,null);
        String value = (String)method1.invoke(annotationTest,1);
        System.out.println(value);


    }
}

