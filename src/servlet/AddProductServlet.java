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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class AddProductServlet extends HttpServlet {
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
            //自定义类型转换器并且注册到当前的BeanUtils中
            ConvertUtils.register(new DateConverterUtil() , Date.class);
            //直接用BeanUtils对Bean对象封装
            BeanUtils.populate(product , req.getParameterMap());
            /**
             * 下面的注释是对BeanUtils和ConvertUtils的测试
             * 需要注意的是，Parameter的name参数必须要和bean中的属性名一致，否则会出现bean不能注入的情况
             * 应为BeanUtils使用的是反射的机制完成对bean对象的注入
             * bean对象必须有(1) 无参的共有构造函数 (2) 所有属性值的get和set方法
            */
//            Map<String,String[]> map = req.getParameterMap();
//            System.out.println(map);
//            Set<String> keySet = map.keySet();
//        for (String key : keySet) {
//            System.out.println(key + "  " + map.get(key)[0]);
//
//        }
//            System.out.println("**************我是一条分割线************");
//            System.out.println(product);
            ProductService productService = new ProductService();
            productService.addProduct(product);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
