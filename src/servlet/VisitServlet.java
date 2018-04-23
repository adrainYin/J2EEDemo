package servlet;

import utils.CookieUtils;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class VisitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 使用cookie进行http请求和访问
         * （1） 获得所有的req的cookies
         * （2） 如果其中不包含上次登陆时间，那么判定为第一次登陆，返回欢迎界面
         * （3） 把当前的登陆时间放进cookie
         * （4） 若不是第一次登陆，则返回上一次登陆时间
         * （5） 对登陆时间更新
         */
        Cookie[] cookies = req.getCookies();
        Cookie cookie = CookieUtils.findCookieByName(cookies , "visitTime");
        resp.setContentType("text/html;charset=UTF-8");

        if (cookie == null){
            resp.getWriter().println("<h4>欢迎来到本网站！<h4>");

        }else {
            Long time = Long.parseLong(cookie.getValue());
            Date date = new Date(time);
            resp.getWriter().println("<h4>欢迎来到本网站！上次登陆时间为" + date.toString() +"!<h4>");
    }

        Cookie visitCookie = new Cookie("visitTime" , ""+System.currentTimeMillis());
        resp.addCookie(visitCookie);
    }
}
