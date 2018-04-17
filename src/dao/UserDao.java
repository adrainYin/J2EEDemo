package dao;

import bean.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.C3p0Utils;

import java.sql.SQLException;

public class UserDao {

    public User login(User user) throws SQLException{
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "SELECT * FROM user WHERE username=? AND password=?";
        User isExistUser = queryRunner.query(sql , new BeanHandler<>(User.class) , user.getUsername() ,
                user.getPassword());
        return isExistUser;

    }
}
