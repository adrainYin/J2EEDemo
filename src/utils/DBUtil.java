package utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class DBUtil {

    private static String driver;
    private static String url;
    private static String user;
    private static String password;
    private static ThreadLocal<Connection> local = new ThreadLocal<>();
    static {
        /*
        使用ResourceBundle获取文件的资源信息
         */
//        ResourceBundle dbBundle = ResourceBundle.getBundle("dbConnection");
//        driver = dbBundle.getString("jdbc.driver");
//        url = dbBundle.getString("jdbc.url");
//        user = dbBundle.getString("jdbc.user");
//        password = dbBundle.getString("jdbc.password");
        /*
        通过类加载器和输入流获得文件的输入信息
         */
        InputStream inputStream = DBUtil.class.getClassLoader().getResourceAsStream("dbConnection");
        //使用properties处理流
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        driver = properties.getProperty("jdbc.driver");
        url = properties.getProperty("jdbc.driver");
        user = properties.getProperty("jdbc.user");
        password = properties.getProperty("jdbc.password");
    }

    /**
     * 静态方法，取得实例化的connection对象
     * @return 已经实例化好的connection对象
     */
    public static Connection getConnection(){
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection connection = DriverManager.getConnection(url , user , password);
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void commitAndClose(Connection connection){
        try {
            connection.commit();
            connection.close();
        } catch (SQLException e) {
            System.out.println("数据库事务提交异常");
            e.printStackTrace();
        }

    }

    public static void rollbackAndClose(Connection connection){
        try {
            connection.rollback();
            connection.close();
        } catch (SQLException e) {
            System.out.println("事务回滚异常");
            e.printStackTrace();
        }

    }

    public static void closeAllResource(ResultSet resultSet , PreparedStatement preparedStatement , Connection connection){
        try {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws SQLException {
            Connection connection = DBUtil.getConnection();
            String sql = "select * from category;";
        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        //boolean isFinished =  statement.execute(sql);
            //System.out.println(isFinished);
        ResultSet resultSet = null;
        try {
            resultSet = statement.executeQuery(sql);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        while (resultSet.next()){
            System.out.println(resultSet.getString(2));
        }
        resultSet.close();
        statement.close();
        connection.close();


    }
}
