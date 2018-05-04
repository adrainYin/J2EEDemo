package service;

import bean.Category;
import bean.Page;
import dao.CategoryDao;

import java.util.List;

public class CategoryService {

    public List<Category> findCateGoryByPage(int pageNumber , int pageSize){
        CategoryDao categoryDao = new CategoryDao();
        int totalNumber = CategoryDao.getTotalRecord();
        Page<Category> categoryPage= new Page<>(pageNumber ,pageSize , totalNumber);
        int startIndex = categoryPage.getStartIndex();
        List<Category> categoryList = categoryDao.findCategoryByPage(startIndex , pageSize);
        return categoryList;
    }
}
