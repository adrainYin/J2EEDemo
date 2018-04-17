package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

public class ReadPropertiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /**
         * 使用ServletContext读取web的资源文件
         * (1)取得当前web项目的ServletContext对象
         * (2)取得资源文件的输入流
         * (3)构建资源文件对象
         * (4)读取资源文件的内容
         */
        ServletContext servletContext = this.getServletContext();
        PrintWriter printWriter = resp.getWriter();
        //(1)  直接使用getSourceStram方法
        InputStream inputStream = servletContext.getResourceAsStream("/WEB-INF/classes/dbConnection.properties");

        //(2) 使用文件绝对路径获得文件流，用文件流构建对象
        //String path = servletContext.getRealPath("/WEB-INF/classes/dbConnection.properties");
        //InputStream inputStream = new FileInputStream(path);
        Properties properties = new Properties();
        properties.load(inputStream);
        String driverManager = properties.getProperty("jdbc.driver");
        String dbUser = properties.getProperty("jdbc.user");
        printWriter.println(driverManager + "    " + dbUser);
    }
}
