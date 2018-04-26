package service;

import bean.Product;
import dao.ProductDao;

import java.util.List;

public class ProductService {

    public List<Product> findAll(){
        ProductDao productDao = new ProductDao();
        return productDao.findAll();
    }

    public void addProduct(Product product){
        ProductDao productDao = new ProductDao();
        productDao.addProduct(product);
    }

    public Product findProductById(String pid){
        ProductDao productDao = new ProductDao();
        Product product = productDao.findProductById(pid);
        return product;
    }

    public int editProduct(Product product){
        ProductDao productDao = new ProductDao();
        return productDao.editProduct(product);
    }
}
