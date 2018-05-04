package servlet;

import bean.TestProduct;
import net.sf.json.JSONArray;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class ProductFindByWordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");

        String word = req.getParameter("word");
        ProductService productService = new ProductService();
        List<TestProduct> list = productService.findProductByWord(word);
        String jsonData = JSONArray.fromObject(list).toString();
        resp.getWriter().println(jsonData);
    }
}
