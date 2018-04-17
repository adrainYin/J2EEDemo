package test;

import bean.Category;
import com.mchange.v2.c3p0.test.C3P0BenchmarkApp;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.C3p0Utils;
import utils.DBCPUtils;
import utils.DBPoolUtils;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class TestDBUtils {
    public static void main(String[] args) throws SQLException {
//        for (int i = 0; i < 10; i++) {
//            new DBThread().start();
//        }
           //使用c3p0获得数据库的连接对象
//        Connection connection = C3p0Utils.getConnection();
//        System.out.println(connection);
          //使用DBCP获得数据库的连接对象
        //使用DBUtils取得数据源
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        /*
        进行更新操作
         */
//        String sql = "INSERT INTO category(cid,cname) VALUES(?,?)";
//        Object[] parms = {"c004" , "computer"};
//        int op =  queryRunner.update(sql , parms);
//        System.out.println("插入成功 ， 返回操作码"  + op);

        /*
        删除操作
         */
//        String deleteSql = "DELETE FROM category WHERE cid=?";
//        Object[] deleteparms = {"c004"};
//        int deleteop = queryRunner.update(deleteSql , deleteparms);
//        System.out.println("删除成功， 返回操作码 " + deleteop);

        /*
        使用id查询操作 ， 直接返回bean对象
         */
//        String sql = "SELECT * FROM category WHERE cid=?";
//        Object[] prams = {"c003"};
//        Category category = queryRunner.query(sql , new BeanHandler<Category>(Category.class) , prams);
//        System.out.println(category);
        /*
        查询所有操作
         */
//        String sql = "SELECT * FROM category c,product p WHERE c.cid=p.category_id";
//        Object[] parms = {};
//        List rsList = queryRunner.query(sql , new ArrayListHandler(),parms);
//        System.out.println(rsList.size());
//        System.out.println(rsList.get(0));

        /*
        查询单个数据
         */
        /**
         * 这里的parms是预编译中的参数匹配项，必须要有。如果代码中没有预编译参数那么该对象数组设为空值
         */
        String sql = "SELECT count(*) FROM category";
        Object[] parms = {};
        Long count = (Long) queryRunner.query(sql , new ScalarHandler<>() , parms);
        System.out.println(count.intValue());
    }
}
