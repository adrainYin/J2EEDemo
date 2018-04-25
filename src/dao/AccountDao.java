package dao;

import utils.C3p0Utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AccountDao {

    public void outMoney(String payorName , double money){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        //ResultSet resultSet = null;
        connection = C3p0Utils.getConnection();
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

    public void inMoney(String payeeName , double money){
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        connection = C3p0Utils.getConnection();
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
