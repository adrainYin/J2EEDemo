package servlet;

import bean.Product;
import org.apache.commons.beanutils.BeanUtils;
import service.ProductService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.List;

public class FindProductByNameAndCidServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
        Product product = new Product();
        try {
            BeanUtils.populate(product , req.getParameterMap());
            System.out.println(product);
            ProductService productService = new ProductService();
            List<Product> productList = productService.findProductByNameAndCid(product);

            //将数据回显到前端
            req.setAttribute("allProduct" , productList);
            req.setAttribute("product" , product);
            req.getRequestDispatcher("/product_list.jsp").forward(req,resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
