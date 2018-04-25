package service;

import dao.AccountDao;
import utils.C3p0Utils;

import java.sql.Connection;
import java.sql.SQLException;

public class AccountService {
    /**
     * Service业务逻辑，从Servlet层接收数据，并将数据封装，调用dao层实现真正的业务.
     * 定义两个业务：
     * (1) 扣钱
     * (2) 加钱
     * @param payorName 扣钱人姓名
     * @param payeeName 进钱人姓名
     * @param money 金钱数
     */
    public void transfer(String payorName , String payeeName , double money){
        Connection connection = C3p0Utils.getConnection();
        try {
            connection.setAutoCommit(false);
            AccountDao accountDao = new AccountDao();
            accountDao.outMoney(payorName , money);
            accountDao.inMoney(payeeName, money);
            connection.commit();
            connection.close();
            //DBUtil.commitAndClose(connection);
            System.out.println("事务提交成功");

        } catch (SQLException e) {
//            if (connection != null){
//                DBUtil.rollbackAndClose(connection);
//                System.out.println("事务提交失败");
//            }
//            throw new RuntimeException();
        }

    }
}
