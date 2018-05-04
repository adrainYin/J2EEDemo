package service;

import bean.Product;
import bean.TestProduct;
import dao.ProductDao;

import java.util.ArrayList;
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

    public List<Product> findProductByNameAndCid(Product product){
        StringBuffer stringBuffer = new StringBuffer();
        List<Object> paramsList = new ArrayList<>();

        //  如果返回的条件中包含cid ， 那么在数据库查询中加入cid
        if (product.getCid() != null && !"".equals(product.getCid())){
            stringBuffer.append("AND cid = ?");
            paramsList.add(product.getCid());
        }

        //如果在返回的条件中包含商品名的模糊搜索， 那么在数据库查询中就加入pname
        if(product.getPname() != null && !"".equals(product.getPname())){
            stringBuffer.append(" AND pname LIKE ?");
            paramsList.add("%" + product.getPname() + "%");
        }

        //将数据类型转换为数据库查询可以接受的数据类型
        String sqlBuffer = stringBuffer.toString();
        Object[] params = paramsList.toArray();
        ProductDao productDao = new ProductDao();
        return productDao.findProductByCondition(sqlBuffer , params);
    }

    public List<TestProduct> findProductByWord(String word){
        StringBuilder stringBuilder = new StringBuilder();
        List<Object> paramList = new ArrayList<>();

        //将字符串拆分为% % %的格式 ，其中用%开始 ， 用%结束
        if (word != null) {
            StringBuilder wordBuilder = new StringBuilder();
            wordBuilder.append("%");
            for (int i = 0; i < word.length(); i++) {
                wordBuilder.append(word.charAt(i)).append("%");
            }

            stringBuilder.append("AND pname LIKE ?");
            paramList.add(wordBuilder.toString());
            //将字符串数据两次加入查询条件
            //如果是中文，那么第一次就可以匹配成功，如果是拼音那么第二个条件可以匹配成功而第一个条件一定不会匹配成功
            stringBuilder.append("OR pinyin LIKE ?");
            paramList.add(wordBuilder.toString());
        }

        String condition = stringBuilder.toString();
        Object[] params = paramList.toArray();

        ProductDao productDao = new ProductDao();
        return productDao.findProductByWord(condition , params);
    }
}
