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



    public static DataSource getDefaultDataSource(){
        return dataSource;

    }

    public static DataSource getDataSource(){
        return dataSource;
    }

    public static Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("获得连接失败");
        return null;
    }
}
