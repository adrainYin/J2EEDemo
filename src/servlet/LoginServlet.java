package servlet;

import bean.User;
import dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        String username = req.getParameter("username");
        String passwprd = req.getParameter("password");
        String autoLogin = req.getParameter("autoLogin");

        //设置自动登陆的选项
        if (autoLogin != null){
            Cookie cookie = new Cookie("userLogin",username + "&&" + passwprd);
            cookie.setMaxAge(60 * 60);
            resp.addCookie(cookie);
        }
        //数据库查询是否存在
        UserDao userDao = new UserDao();
        User loginUser = userDao.findUser(username , passwprd);
        //查询成功，存在此用户
        if (loginUser != null){
            req.getSession().setAttribute("loginUser" , loginUser);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");

        }
        else {
            System.out.println("查无此人");
            req.setAttribute("msg" , "查询结果不存在");
            req.getRequestDispatcher(req.getContextPath() + "/userLogin.jsp").forward(req,resp);
        }
    }
}
