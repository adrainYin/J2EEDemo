package filter;

import bean.User;
import service.UserService;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest){
            System.out.println("request是HttpServletRequest的实例化对象");
        }
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;

        User loginUser = (User)req.getSession().getAttribute("userLogin");

        //判断是否已经登陆，如果已经登陆那么就不需要此filter之后的操作
        if (loginUser != null){
            System.out.println("该用户已经在登陆");
            filterChain.doFilter(req , resp);
            return;
        }

        //从cookie中遍历用户名和密码的cookie
        Cookie[] cookies = req.getCookies();
        Cookie userCookie = null;
        if (cookies != null){
            for (Cookie cookie : cookies){
                if ("userLogin".equals(cookie.getName())){
                    userCookie = cookie;
                    break;
                }
            }
        }else {
            System.out.println("cookie为空");
        }
        //说明cookie中没有存放用户的记录
        if (userCookie == null){
            System.out.println("查找的cookie为空");
            filterChain.doFilter(req, resp);
        }

        String[] userMessage = userCookie.getValue().split("&&");
        UserService userService = new UserService();
        loginUser = userService.findUser(userMessage[0] , userMessage[1]);
        //数据库中没有此用户的信息
        if (loginUser == null){
            System.out.println("用户名或密码错误");
            filterChain.doFilter(req, resp);
        }

        //否则就自动登陆
        req.getSession().setAttribute("userLogin" , loginUser);
        System.out.println("使用过滤器登陆成功");
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {

    }
}
