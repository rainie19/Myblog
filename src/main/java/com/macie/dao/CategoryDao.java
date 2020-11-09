package com.macie.dao;

import com.macie.bean.vo.CategoryVo;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/9/28 -16:29
 */
public interface CategoryDao {
    /**
     *
     * @return 所有分类
     */
    public ArrayList<CategoryVo> retrieveAllCategories();

    /**
     * 将分类下的所有文章移到默认分类
     * @param categoryId
     * @return
     */
    public int updateCategory2Default(Integer categoryId);
    /**
     *
     * @param categoryId
     * @return
     */
    public int deleteCategoryById(Integer categoryId);

    /**
     *插入新分类
     * @param categoryVo
     * @return
     */
    public int insertNewCategory(CategoryVo categoryVo);

    /**
     * 查询分类是否存在
     * @param categoryName
     * @return
     */
    public Boolean isCategoryExits(String categoryName);
}
