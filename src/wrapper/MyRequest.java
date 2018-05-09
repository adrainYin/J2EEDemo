package wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.UnsupportedEncodingException;

/**
 * 自定义HttpServletRequest的装饰类，对方式进行装饰加强
 */
public class MyRequest extends HttpServletRequestWrapper {

    /**
     * 这里必须用构造函数传入HttpServletRequest的参数对象，在构造函数初始化的同时将父类的构造函数初始化，
     * 使得在后面的方法增强中自定义的类对象可以使用父类的方法
     * @param request 父类参数对象
     */
    public MyRequest(HttpServletRequest request){
        super(request);
    }

    /**
     * 方法增强，在调用父类方法获得Parameter参数的同时设定字符编码集
     * 注意方法的覆写仅仅针对本方法，但是还是可以调用父类已经有的方法。相当于直接调用request的方法
     * @param name
     * @return
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if ("GET".equalsIgnoreCase(super.getMethod())){
            try {
                value = new String(value.getBytes("ISO-8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException();
            }
        }
        return value;
    }
}
