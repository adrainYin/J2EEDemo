package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = req.getServletContext();
        servletContext.setAttribute("user" ,  "yinchenhao");
        String user = (String) servletContext.getAttribute("user");
        //对servletContext的内容进行更新， 会调用监听器的replace方法。但是getVlaue()方法返回的是修改之前的旧的值。
        servletContext.setAttribute("user" , "ECNU");
        servletContext.removeAttribute("user");

        //在这一行代码中自动创建了session对象， 如果存在session对象则直接返回
        HttpSession session = req.getSession();
        session.invalidate();

        resp.getWriter().println("hi" + user);

    }
}
