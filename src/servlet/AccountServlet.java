package servlet;

import service.AccountService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        String payorName = req.getParameter("payor");
        String payeeName = req.getParameter("payee");
        double money =  Double.parseDouble(req.getParameter("money"));
        AccountService accountService = new AccountService();
        accountService.transfer(payorName , payeeName , money);

            resp.getWriter().println("<h1>转账汇款成功成功！</h1>");
            resp.getWriter().println(payorName + "向" + payeeName + "转账" + money + "元");

            //resp.getWriter().println("转账失败！请重试");
    }
}
