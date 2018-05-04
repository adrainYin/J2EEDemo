package servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + "  " + password);
        resp.getWriter().println("输出成功");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//
//
//        String username = req.getParameter("username");
//        String pwd = req.getParameter("pwd");
//        System.out.println(username + "   " + pwd);
//
//        String hobbys[] = req.getParameterValues("hobby");
//        System.out.println(Arrays.toString(hobbys));
//        System.out.println("****************我是一条分割线*************");
//        Map<String,String[]> data = req.getParameterMap();
//        //这里定义了map的遍历方法，将map转换为set,并且对于每一个set都取得其key值和value值
//        for (Map.Entry<String,String[]> entry : data.entrySet()){
//            System.out.print(entry.getKey());
//            System.out.print("-->");
//            System.out.println(Arrays.toString(entry.getValue()));
//
//        }
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        System.out.println(username + "    " + password);
        resp.getWriter().println("post成功");

    }
}
