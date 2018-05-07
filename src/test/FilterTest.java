package test;

import javax.servlet.*;
import java.io.IOException;

public class FilterTest implements Filter {
    /**
     *
     * @param filterConfig 该参数是filter的配置信息参数，配置信息参数可以从web.xml文件中获得
     *                     在Filter初始化的时候，使用该参数会获得filter的配置信息
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("filter的初始化");

        String filterName = filterConfig.getFilterName();
        //这里得到的直接就是String类型的字符串，而不是一般看到的Object类型数据
        String value = filterConfig.getInitParameter("test");
        System.out.println(filterName + "    " + value);
    }

    /**
     * 过滤器的调用逻辑，每当客户端访问web资源时，web容器陡先会对访问进行拦截，并且调用Filter的Servlet方法
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        System.out.println("开始进行过滤操作");
        /**
         * 这行代码是过滤器的核心操作，使用了一个filterChain的对象进行doFilter方法
         * 因为在web服务中可能同时存在多个filter，他们是根据在web.xml中的注册顺序决定过滤的顺序。
         * 那么在当前的filter中过滤之后，如果用户调用了filterChain的doFilter()方法，那么web容器会检查FilterChain对象中
         * 是否还存在filter对象，如果有那么将当前的req和resp对象交给下一个Filter处理
         */
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("结束过滤操作");
    }

    @Override
    public void destroy() {
        System.out.println("filter的销毁");
    }
}

