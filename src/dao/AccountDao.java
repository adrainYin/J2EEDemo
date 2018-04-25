package dao;

import utils.C3p0Utils;
import utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDao {

    public void outMoney(Connection connection, String payorName , double money){

        PreparedStatement preparedStatement = null;
        //ResultSet resultSet = null;
        if (connection == null){
            connection = C3p0Utils.getConnection();
        }

        String sql = "UPDATE account SET money = money - ? WHERE name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1,money);
            preparedStatement.setString(2,payorName);
            int result = preparedStatement.executeUpdate();
            System.out.println("出钱的状态码是" + result);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inMoney(Connection connection,String payeeName , double money){
        PreparedStatement preparedStatement = null;
        if (connection == null){
            connection = C3p0Utils.getConnection();
        }
        String sql = "UPDATE account SET money = money + ? WHERE name = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1 , money);
            preparedStatement.setString(2 , payeeName);
            int result = preparedStatement.executeUpdate();
            System.out.println("入钱状态码是" + result);
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

}
