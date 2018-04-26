package servlet;

import bean.Product;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FindProductByIdServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        String pid = req.getParameter("pid");
        ProductService productService = new ProductService();
        Product product = productService.findProductById(pid);
        System.out.println(product.toString());
        if (product != null) {
            req.setAttribute("product", product);
            req.getRequestDispatcher("/product_edit.jsp").forward(req, resp);
        }else {
            resp.getWriter().println("<h1>查询失败！不存在指定商品</h1>");
        }
    }
}
