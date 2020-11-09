package com.macie.service;

import com.macie.bean.vo.CategoryVo;
import com.macie.dao.CategoryDao;
import com.macie.dao.daoImpl.CategoryDaoImpl;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/9/29 -16:37
 */
public class CategoryService implements CategoryDao {
    CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();

    @Override
    public ArrayList<CategoryVo> retrieveAllCategories() {
        return categoryDaoImpl.retrieveAllCategories();
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
    public Boolean isCategoryExits(String categoryName) {
        return null;
    }

}
