package com.macie.service;

import com.macie.bean.vo.CategoryVo;
import com.macie.dao.CategoryDao;
import com.macie.dao.daoImpl.ArticleDaoImpl;
import com.macie.dao.daoImpl.CategoryDaoImpl;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Macie
 * @date 2020/9/29 -16:37
 */
public class CategoryService implements CategoryDao {
    private static final CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
    private static final ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();

    @Override
    public ArrayList<CategoryVo> listAllCategories() {
        return categoryDaoImpl.listAllCategories();
    }

    @Override
    public Long countAllCategories() {
        return categoryDaoImpl.countAllCategories();
    }

    @Override
    public int updateCategory2Default(Integer categoryId) {
        return categoryDaoImpl.updateCategory2Default(categoryId);
    }

    @Override
    public int deleteCategoryById(Integer categoryId) {
        return categoryDaoImpl.deleteCategoryById(categoryId);
    }

    @Override
    public int insertNewCategory(CategoryVo categoryVo) {
        return categoryDaoImpl.insertNewCategory(categoryVo);
    }

    @Override
    public Boolean isCategoryExists(String categoryName) {
        return categoryDaoImpl.isCategoryExists(categoryName);
    }

    public TreeMap<String, Long> countArticlesEachCategory(ArrayList<CategoryVo> categoryVos) {
        TreeMap<String, Long> countMap = new TreeMap<>();
        if (categoryVos != null) {
            for (CategoryVo category : categoryVos) {
                String categoryName = category.getCategoryName();
                Long count = articleDaoImpl.countAllArticles("category", categoryName);
                countMap.put(categoryName, count);
            }
        }
        return countMap;
    }

}
