package servlet;

import bean.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UserServlet  extends HttpServlet {

//    @Override
//    public void init() throws ServletException {
//        int count = 0;
//        /**
//         * 定义ServletContext的内容，ServletContext的内容在整个Servlet的生命周期中都一直存在。
//         * 每个web项目唯一
//         * ServletContext提供了一种键值对的map类型
//         * 定义了三个方法：setAttribute(String , Object) , getAttribute(String) , removeAttribute(String)
//         */
//        this.getServletContext().setAttribute("count" , count);
//    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setHeader("Content-type", "text/html;charset=UTF-8");

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String checkcode = req.getParameter("checkcode");
        String realCheckcode = (String)req.getSession().getAttribute("checkcode");
        System.out.println(realCheckcode);
        System.out.println(checkcode);
        if (!checkcode.equals(realCheckcode)){
            req.setAttribute("msg" , "验证码输入错误！");
            //地址的重定向，是客户端向服务器提出的重定向请求，不改变原url，只是服务器将内容加载过来。请求头信息没有改变
            req.getRequestDispatcher("/login.jsp").forward(req,resp);
        }

        User user = new User(username , password);
        UserService userService = new UserService();

        try {
            User isExist = userService.loginService(user);
            if (isExist == null){
                resp.getWriter().println("登陆失败");
            }else {
                resp.getWriter().println("登陆成功");
                int count = (int)this.getServletContext().getAttribute("count");
                count++;
                this.getServletContext().setAttribute("count",count);
                resp.getWriter().println("您是第" + count +"位登陆者");
                resp.getWriter().println(this.getServletConfig().getServletName());
                //resp.getWriter().println(this.getServletConfig().getInitParameter("count"));
                resp.getWriter().println(this.getServletConfig().getInitParameterNames());

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
