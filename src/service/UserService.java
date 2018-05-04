package service;

import bean.User;
import dao.UserDao;

import java.sql.SQLException;

public class UserService {

    public User loginService(User user) throws SQLException {
        UserDao userDao = new UserDao();
        return userDao.login(user);
    }

    public User findUserByUsername(String username){
        UserDao userDao = new UserDao();
        return userDao.findUserByUsername(username);
    }
}
