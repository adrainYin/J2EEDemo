package test;

import bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.C3p0Utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBUtilTest {

    public static void main(String[] args) {
       // Connection connection = C3p0Utils.getConnection();
       // System.out.println(connection);
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        try {
            User user =  queryRunner.query(sql , new BeanHandler<User>(User.class), "mike" , "123000");
            System.out.println(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        try {
//            User isExistUser = queryRunner.query(sql , new BeanHandler<>(User.class) , "mike" ,
//                    "123000");
//            System.out.println(isExistUser.getUsername() +  "   " + isExistUser.getPassword());
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}