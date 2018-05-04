package dao;

import bean.Category;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import utils.C3p0Utils;

import java.sql.SQLException;
import java.util.List;

public class CategoryDao {

    public static int getTotalRecord(){
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "SELECT COUNT(*) FROM category";
        try {
            Long count = queryRunner.query(sql , new ScalarHandler<>());
            return count.intValue();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<Category> findCategoryByPage(int startIndex, int pageSize){
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "SELECT * FROM category LIMIT ? , ?";
        Object[] params  = {startIndex , pageSize};
        System.out.println(params[0]);
        try {
            List<Category> categorieList = queryRunner.query(sql , new BeanListHandler<>(Category.class) , params);
            return categorieList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
