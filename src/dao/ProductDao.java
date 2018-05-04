package dao;

import bean.Product;
import bean.TestProduct;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.junit.jupiter.api.Test;
import utils.C3p0Utils;

import java.sql.SQLException;
import java.util.List;

public class ProductDao {

    public List<Product> findAll(){
        try{
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "SELECT * FROM product";
        Object[] params = {};

        return queryRunner.query(sql , new BeanListHandler<>(Product.class) , params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addProduct(Product product){
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {product.getPid(),product.getPname(),product.getMarket_price(),
                           product.getShop_price(),product.getPimage(),product.getPdate(),
                           product.getIs_hot(),product.getPdesc(),product.getPflag(),
                           product.getCid()};
        try {
            queryRunner.update(sql,params);
            System.out.println("插入数据成功");
        } catch (SQLException e) {
            System.out.println("插入数据失败");
            throw new RuntimeException(e);
        }
    }

    public Product findProductById(String pid){
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "SELECT * FROM product WHERE pid = ?";
        Object[] params = {pid};
        try {
            Product product = queryRunner.query(sql , new BeanHandler<>(Product.class), params);
            if (product == null){
                System.out.println("查询失败");
                return null;
            }
            else {
                System.out.println("查询成功");
                return product;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int editProduct(Product product){
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "UPDATE product SET pname=? , market_price=? , shop_price=? , pimage=? , " +
                "pdate=? , is_hot=? , pdesc=? , pflag=? WHERE pid=?";
        Object[] params = {product.getPname() , product.getMarket_price() , product.getShop_price(),
                           product.getPimage() , product.getPdate() , product.getIs_hot() ,
                           product.getPdesc() , product.getPflag() , product.getPid()};
        try {
            int states = queryRunner.update(sql , params);
            System.out.println("更新操作的状态码为" + states);
            if (states != 1){
                System.out.println("更新失败");
                return 0;
            }
            else {
                System.out.println("更新成功");
                return 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public List<Product> findProductByCondition(String sqlBuffer , Object[] params){
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        /**
         * 在QueryRunner匹配模糊查询的时候不能匹配到%%符号 ，所以要将符号和参数值一起放入params中
         */
        String sql = "SELECT * FROM product WHERE 1=1 " + sqlBuffer;
        try {
            return queryRunner.query(sql , new BeanListHandler<>(Product.class) , params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<TestProduct> findProductByWord(String condition , Object[] params){
        QueryRunner queryRunner = new QueryRunner(C3p0Utils.getDataSource());
        String sql = "SELECT * FROM testproduct WHERE 1=1 " + condition;
        try {
             return queryRunner.query(sql , new BeanListHandler<>(TestProduct.class) , params);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
