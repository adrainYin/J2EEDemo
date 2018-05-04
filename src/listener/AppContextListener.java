package listener;

import manager.DBConnectionManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext servletContext= servletContextEvent.getServletContext();
        String dbURL = servletContext.getInitParameter("dbURL");
        String user = servletContext.getInitParameter("dbuser");
        String password = servletContext.getInitParameter("dbpassword");

        DBConnectionManager dbConnectionManager = new DBConnectionManager(dbURL , user , password);
        servletContext.setAttribute("dbConnectionManager" , dbConnectionManager);
        System.out.println("初始化连接对象");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ServletContext servletContext = servletContextEvent.getServletContext();
        DBConnectionManager dbConnectionManager = (DBConnectionManager)servletContext.getAttribute("dbConnectionManager");
        dbConnectionManager.closeConnection();
        System.out.println("关闭连接对象");
    }
}
