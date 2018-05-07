package listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 在web容器启动的时候ServletContext对象直接进行了初始化
 * 在Servlet程序请求访问的时候先进行req对象的初始化，在Servlet程序结束的时候进行req对象的销毁
 * Session在第一次getSession的时候进行创建，session对象并不是一定存在的
 */
public class MyServletRequestListener implements ServletRequestListener {
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        System.out.println("request对象销毁" + servletRequestEvent.getServletRequest().getRemoteAddr());
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        //在request对象创建和销毁的时候返回request的IP地址
        System.out.println("request对象创建" + servletRequestEvent.getServletRequest().getRemoteAddr());
    }
}
