package filter;

import wrapper.MyRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        //自定义装饰类，在此后request对象传递的同时设定了字符编码集
        MyRequest myRequest = new MyRequest(req);
        req.setCharacterEncoding("UTF-8");
        filterChain.doFilter(myRequest, resp);
    }

    @Override
    public void destroy() {

    }
}
