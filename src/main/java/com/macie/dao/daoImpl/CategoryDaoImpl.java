package com.macie.dao.daoImpl;

import com.macie.bean.vo.CategoryVo;
import com.macie.dao.CategoryDao;
import com.macie.dao.constant.DatabaseConsts;
import com.macie.util.dbutil.DbChangeUtils;
import com.macie.util.dbutil.DbRetrieveUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Macie
 * @date 2020/9/28 -16:28
 */
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public ArrayList<CategoryVo> listAllCategories() {
        String sql = "SELECT " + DatabaseConsts.CATEGORY_ID + ", " + DatabaseConsts.CATEGORY_NAME +
                " FROM " + DatabaseConsts.TABLE_BLOG_CATEGORY;
        return DbRetrieveUtils.retrieveBeanListByParams(sql, null, CategoryVo.class);
    }

    @Override
    public int updateCategory2Default(Integer categoryId) {
        String sql = "UPDATE " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
                " SET " + DatabaseConsts.CATEGORY_NAME + " = " + DatabaseConsts.DEFAULT_CATEGORY +
                " WHERE " + DatabaseConsts.CATEGORY_NAME +
                " = SELECT " + DatabaseConsts.CATEGORY_NAME +
                " FROM " + DatabaseConsts.TABLE_BLOG_CATEGORY +
                " WHERE " + DatabaseConsts.CATEGORY_ID + " = ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(categoryId);
        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public int deleteCategoryById(Integer categoryId) {
        String sql = "DELETE FROM " + DatabaseConsts.TABLE_BLOG_CATEGORY +
                " WHERE " + DatabaseConsts.CATEGORY_ID + " = ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(categoryId);
        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public int insertNewCategory(CategoryVo categoryVo) {
                String sql = "INSERT INTO " + DatabaseConsts.TABLE_BLOG_CATEGORY + " (" +
                        DatabaseConsts.CATEGORY_NAME + ", " +
                        DatabaseConsts.CREATE_DATE + ", " +
                        DatabaseConsts.CATEGORY_DELETE +
                        ") VALUES (?, ?, ?)";
        ArrayList<Object> list = new ArrayList<>();
        list.add(categoryVo.getCategoryName());
        list.add(new Date());
        list.add(1);
        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public Long countAllCategories() {
        String sql = "SELECT COUNT(*) FROM " + DatabaseConsts.TABLE_BLOG_CATEGORY;
        return DbRetrieveUtils.retrieveBasicTypeByParams(sql, null, Long.class);
    }

    @Override
    public Boolean isCategoryExists(String categoryName) {
        String sql = "SELECT IFNULL((SELECT 'true' FROM " +
                DatabaseConsts.TABLE_BLOG_CATEGORY + " WHERE " + DatabaseConsts.CATEGORY_NAME + " = ? ), 'false')";
        ArrayList<Object> list = new ArrayList<>();
        list.add(categoryName);
        String isExist = DbRetrieveUtils.retrieveBasicTypeByParams(sql, list, String.class);
        return Boolean.parseBoolean(isExist);
    }
}
