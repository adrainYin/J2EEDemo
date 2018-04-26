package servlet;

import bean.Product;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import service.ProductService;
import utils.DateConverterUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

public class ProductEditServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html;charset=UTF-8");

        ConvertUtils.register(new DateConverterUtil() , Date.class);
        Product product = new Product();
        try {
            BeanUtils.populate(product , req.getParameterMap());
            ProductService productService = new ProductService();
            int states =  productService.editProduct(product);
            if (states == 1){
                resp.getWriter().println("更新数据成功");
            }
            else {
                resp.getWriter().println("更新数据失败");
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
