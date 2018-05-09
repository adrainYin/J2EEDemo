package demo;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 测试类的主程序
 */
public class RunTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        int errorMun = 0;
        StringBuffer log = new StringBuffer();

        Class cls = Class.forName("demo.TestDemo");
        Method[] methods = cls.getDeclaredMethods();
        TestDemo testDemo = (TestDemo) cls.newInstance();

        for (Method testMethod : methods){
            if (testMethod.isAnnotationPresent(MyTestUtil.class)){
                testMethod.setAccessible(true);
                try {
                    testMethod.invoke(testDemo , null);
                    System.out.println("方法" + testMethod.getName()+ "测试通过\n");
                } catch (InvocationTargetException e) {
                    errorMun++;
                    log.append(testMethod.getName()).append("发生了错误").append("\n\r");
                    log.append("原因是因为").append(e.getCause().getClass().getSimpleName()).append("\n\r");
                    log.append(e.getCause().getMessage()).append("\n\r");
                }

            }
        }

        log.append("\n一共发生了" + errorMun + "个错误").append("\n\r");
        System.out.println(log);
    }
}
