package servlet;

import bean.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String username = req.getParameter("username");

        //定义json数据
        String jsonData = "";
        UserService userService = new UserService();
        User user = userService.findUserByUsername(username);
        System.out.println(user);

        if (user != null){
            jsonData = "{\"message\":\"用户名不可用\",\"flag\":false}";
        }else{
          jsonData =  "{\"message\":\"用户名可用\",\"flag\":true}";
        }

        resp.getWriter().println(jsonData);
    }
}
