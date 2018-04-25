package utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class C3p0Utils {
    //使用默认配置
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();
    //使用自定义的配置
    //private static ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource("myConfig");
    /**
     * 定义线程局部变量
     */
    private static ThreadLocal<Connection> local = new ThreadLocal<>();




    public static DataSource getDefaultDataSource(){
        return dataSource;

    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection(){
//        try {
//            return dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        System.out.println("获得连接失败");
//        return null;
        /**
         * 用ThreadLocal类重构取得连接对象方法
         * ThreadLocal类定义了线程的局部变量属性，该属性在线程了内部是线程安全的
         */
        Connection connection = local.get();
        if (connection == null){
            try {
                connection = dataSource.getConnection();
                local.set(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
