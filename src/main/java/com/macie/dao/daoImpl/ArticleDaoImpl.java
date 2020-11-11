package com.macie.dao.daoImpl;

import com.macie.bean.vo.ArticleVo;
import com.macie.dao.ArticleDao;
import com.macie.dao.constant.DatabaseConsts;
import com.macie.util.dbutil.DbChangeUtils;
import com.macie.util.dbutil.DbRetrieveUtils;

import java.util.ArrayList;
import java.util.Date;


/**
 * @author Macie
 * @date 2020/9/25 -17:20
 */
public class ArticleDaoImpl implements ArticleDao {
    @Override
    public ArrayList<ArticleVo> listAllArticles() {
        String sql = "SELECT " + DatabaseConsts.ARTICLE_ID + ", " +
                DatabaseConsts.ARTICLE_TITLE + ", " +
                DatabaseConsts.ARTICLE_SLUG + ", " +
                DatabaseConsts.ARTICLE_AUTHOR + ", " +
                DatabaseConsts.ARTICLE_SUMMARY + ", " +
                DatabaseConsts.CATEGORY_NAME + ", " +
                DatabaseConsts.ARTICLE_CREATE_TIME + ", " +
                DatabaseConsts.ARTICLE_COMMENTS_COUNT + ", " +
                DatabaseConsts.ARTICLE_VIEW_COUNT +
                " FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
                " ORDER BY " + DatabaseConsts.ARTICLE_ID + " DESC ";
        return DbRetrieveUtils.retrieveBeanListByParams(sql, null, ArticleVo.class);
    }

    @Override
    public ArrayList<ArticleVo> listArticlesPerPage(Integer pageNumber, int pageSize) {
        String sql = "SELECT " + DatabaseConsts.ARTICLE_ID + ", " +
                DatabaseConsts.ARTICLE_TITLE + ", " +
                DatabaseConsts.ARTICLE_SLUG + ", " +
                DatabaseConsts.ARTICLE_AUTHOR + ", " +
                DatabaseConsts.ARTICLE_SUMMARY + ", " +
                DatabaseConsts.CATEGORY_NAME + ", " +
                DatabaseConsts.ARTICLE_CREATE_TIME + ", " +
                DatabaseConsts.ARTICLE_COMMENTS_COUNT + ", " +
                DatabaseConsts.ARTICLE_VIEW_COUNT +
                " FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
                " ORDER BY " + DatabaseConsts.ARTICLE_ID + " DESC " +
                " LIMIT " + pageSize + " OFFSET " + (pageNumber-1)*pageSize;
        return DbRetrieveUtils.retrieveBeanListByParams(sql, null, ArticleVo.class);
    }

    @Override
    public ArticleVo getArticleById(Integer articleId) {
        String sql = "SELECT * FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
                " WHERE " + DatabaseConsts.ARTICLE_ID + " = ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(articleId);
        return DbRetrieveUtils.retrieveBeanByParams(sql, list, ArticleVo.class);
    }

    @Override
    public int insertNewArticle(ArticleVo articleVo) {
        String sql = "INSERT INTO " + DatabaseConsts.TABLE_ARTICLE_DETAILS + "(" +
                DatabaseConsts.ARTICLE_TITLE + ", " +
                DatabaseConsts.ARTICLE_SLUG + ", " +
                DatabaseConsts.ARTICLE_AUTHOR + ", " +
                DatabaseConsts.ARTICLE_SUMMARY + ", " +
                DatabaseConsts.CATEGORY_NAME + ", " +
                DatabaseConsts.ARTICLE_CREATE_TIME + ", " +
                DatabaseConsts.ARTICLE_CONTENT_HTML + ", " +
                DatabaseConsts.ARTICLE_CONTENT_MD + ", " +
                DatabaseConsts.ARTICLE_COMMENTS_COUNT + ", " +
                DatabaseConsts.ARTICLE_VIEW_COUNT +
                ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        ArrayList<Object> list = new ArrayList<>();
        list.add(articleVo.getArticleTitle());
        list.add(articleVo.getArticleSlug());
        list.add(articleVo.getArticleAuthor());
        list.add(articleVo.getArticleSummary());
        list.add(articleVo.getCategoryName());
        list.add(new Date());
        list.add(articleVo.getArticleContentHtml());
        list.add(articleVo.getArticleContentMd());
        list.add(0);
        list.add(0);
        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public int updateArticle(ArticleVo articleVo) {
        String sql = "UPDATE " + DatabaseConsts.TABLE_ARTICLE_DETAILS + " SET " +
                DatabaseConsts.ARTICLE_ID + " = ?, "+
                DatabaseConsts.ARTICLE_AUTHOR + " = ?, " +
                DatabaseConsts.ARTICLE_TITLE + " = ?, " +
                DatabaseConsts.ARTICLE_SLUG + " = ?, " +
                DatabaseConsts.ARTICLE_SUMMARY + " = ?, " +
                DatabaseConsts.CATEGORY_NAME + " = ?, " +
                DatabaseConsts.ARTICLE_CONTENT_HTML + " = ?, " +
                DatabaseConsts.ARTICLE_CONTENT_MD + " = ? " +
                " WHERE " + DatabaseConsts.ARTICLE_ID + " = ? ";

        ArrayList<Object> list = new ArrayList<>();
        list.add(articleVo.getArticleId());
        list.add(articleVo.getArticleAuthor());
        list.add(articleVo.getArticleTitle());
        list.add(articleVo.getArticleSlug());
        list.add(articleVo.getArticleSummary());
        list.add(articleVo.getCategoryName());
        list.add(articleVo.getArticleContentHtml());
        list.add(articleVo.getArticleContentMd());
        list.add(articleVo.getArticleId());
        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public int updateArticleViewCount(Integer articleId) {
        String sql = "UPDATE " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
                " SET " + DatabaseConsts.ARTICLE_VIEW_COUNT + " = " + DatabaseConsts.ARTICLE_VIEW_COUNT + " + 1 " +
                " WHERE " + DatabaseConsts.ARTICLE_ID + " = ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(articleId);
        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public int deleteArticle(Integer articleId) {
        String sql = "DELETE FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
                " WHERE " + DatabaseConsts.ARTICLE_ID + " = ?";
        ArrayList<Object> list = new ArrayList<>();
        list.add(articleId);
        return DbChangeUtils.changeDatabase(sql, list);
    }

    @Override
    public ArticleVo getPreviousArticle(Integer articleId) {
        String sql = "SELECT " + DatabaseConsts.ARTICLE_ID + ", " +
                DatabaseConsts.ARTICLE_TITLE + ", " +
                DatabaseConsts.ARTICLE_SLUG +
                " FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
                " WHERE " + DatabaseConsts.ARTICLE_ID +
                " = (SELECT min(" + DatabaseConsts.ARTICLE_ID + ") FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS + " WHERE " + DatabaseConsts.ARTICLE_ID + " > ? )" ;
        ArrayList<Object> list = new ArrayList<>();
        list.add(articleId);
        return DbRetrieveUtils.retrieveBeanByParams(sql, list, ArticleVo.class);
    }

    @Override
    public ArticleVo getNextArticle(Integer articleId) {
        String sql = "SELECT " + DatabaseConsts.ARTICLE_ID + ", " +
                DatabaseConsts.ARTICLE_TITLE + ", " +
                DatabaseConsts.ARTICLE_SLUG +
                " FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
                " WHERE " + DatabaseConsts.ARTICLE_ID +
                " = (SELECT max(" + DatabaseConsts.ARTICLE_ID + ") FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS + " WHERE " + DatabaseConsts.ARTICLE_ID + " < ? )" ;
        ArrayList<Object> list = new ArrayList<>();
        list.add(articleId);
        return DbRetrieveUtils.retrieveBeanByParams(sql, list, ArticleVo.class);
    }

    @Override
    public Long countAllArticles() {
        String sql = "SELECT COUNT(*) FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS;
        return DbRetrieveUtils.retrieveBasicTypeByParams(sql, null, Long.class);
    }

    @Override
    public Long countAllArticles(String type, String value) {
        String sql = "SELECT COUNT(*) FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS;
        if ("category".equals(type)) {
            sql += " WHERE " + DatabaseConsts.CATEGORY_NAME + " = ?";
        }
        if ("tag".equals(type)) {
            sql = "SELECT COUNT(*) FROM " + DatabaseConsts.TABLE_TAGS + " WHERE " + DatabaseConsts.TAG_NAME + " = ?";
        }
        ArrayList<Object> list = new ArrayList<>();
        list.add(value);
        return DbRetrieveUtils.retrieveBasicTypeByParams(sql, list, Long.class);
    }

    @Override
    public ArrayList<ArticleVo> listArticlesByCategoryName(String categoryName) {
        String sql = "SELECT " + DatabaseConsts.ARTICLE_ID + ", " +
                DatabaseConsts.ARTICLE_TITLE + ", " +
                DatabaseConsts.ARTICLE_SLUG + ", " +
                DatabaseConsts.ARTICLE_AUTHOR + ", " +
                DatabaseConsts.ARTICLE_SUMMARY + ", " +
                DatabaseConsts.CATEGORY_NAME + ", " +
                DatabaseConsts.ARTICLE_CREATE_TIME + ", " +
                DatabaseConsts.ARTICLE_COMMENTS_COUNT + ", " +
                DatabaseConsts.ARTICLE_VIEW_COUNT +
                " FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS + " WHERE " + DatabaseConsts.CATEGORY_NAME + " = ? " +
                " ORDER BY " + DatabaseConsts.ARTICLE_ID + " DESC ";
        ArrayList<Object> list = new ArrayList<>();
        list.add(categoryName);
        return DbRetrieveUtils.retrieveBeanListByParams(sql, list, ArticleVo.class);
    }

    @Override
    public ArrayList<ArticleVo> listArticlesByCategoryNamePerPage(String categoryName, Integer pageNumber, Integer pageSize) {

        String sql = "SELECT " + DatabaseConsts.ARTICLE_ID + ", " +
                DatabaseConsts.ARTICLE_TITLE + ", " +
                DatabaseConsts.ARTICLE_SLUG + ", " +
                DatabaseConsts.ARTICLE_AUTHOR + ", " +
                DatabaseConsts.ARTICLE_SUMMARY + ", " +
                DatabaseConsts.CATEGORY_NAME + ", " +
                DatabaseConsts.ARTICLE_CREATE_TIME + ", " +
                DatabaseConsts.ARTICLE_COMMENTS_COUNT + ", " +
                DatabaseConsts.ARTICLE_VIEW_COUNT +
                " FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS + " WHERE " + DatabaseConsts.CATEGORY_NAME + " = ? " +
                " ORDER BY " + DatabaseConsts.ARTICLE_ID + " DESC " +
                " LIMIT " + pageSize + " OFFSET " + (pageNumber-1)*pageSize;
        ArrayList<Object> list = new ArrayList<>();
        list.add(categoryName);
        return DbRetrieveUtils.retrieveBeanListByParams(sql, list, ArticleVo.class);
    }

    @Override
    public ArrayList<ArticleVo> listArticlesByTagName(String tagName) {
        String sql = "SELECT " + DatabaseConsts.ARTICLE_ID + ", " +
                DatabaseConsts.ARTICLE_TITLE + ", " +
                DatabaseConsts.ARTICLE_SLUG + ", " +
                DatabaseConsts.ARTICLE_AUTHOR + ", " +
                DatabaseConsts.ARTICLE_SUMMARY + ", " +
                DatabaseConsts.CATEGORY_NAME + ", " +
                DatabaseConsts.ARTICLE_CREATE_TIME + ", " +
                DatabaseConsts.ARTICLE_COMMENTS_COUNT + ", " +
                DatabaseConsts.ARTICLE_VIEW_COUNT +
                " FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
                " WHERE " + DatabaseConsts.ARTICLE_ID +
                " IN (SELECT " + DatabaseConsts.ARTICLE_ID +
                " FROM " + DatabaseConsts.TABLE_TAGS +
                " WHERE " + DatabaseConsts.TAG_NAME + " = ?)" +
                " ORDER BY " + DatabaseConsts.ARTICLE_ID + " DESC ";
        ArrayList<Object> list = new ArrayList<>();
        list.add(tagName);
        return DbRetrieveUtils.retrieveBeanListByParams(sql, list, ArticleVo.class);
    }

    @Override
    public ArrayList<ArticleVo> listArticlesByTagNamePerPage(String tagName, Integer pageNumber, Integer pageSize) {
        String sql = "SELECT " + DatabaseConsts.ARTICLE_ID + ", " +
                DatabaseConsts.ARTICLE_TITLE + ", " +
                DatabaseConsts.ARTICLE_SLUG + ", " +
                DatabaseConsts.ARTICLE_AUTHOR + ", " +
                DatabaseConsts.ARTICLE_SUMMARY + ", " +
                DatabaseConsts.CATEGORY_NAME + ", " +
                DatabaseConsts.ARTICLE_CREATE_TIME + ", " +
                DatabaseConsts.ARTICLE_COMMENTS_COUNT + ", " +
                DatabaseConsts.ARTICLE_VIEW_COUNT +
                " FROM " + DatabaseConsts.TABLE_ARTICLE_DETAILS +
                " WHERE " + DatabaseConsts.ARTICLE_ID +
                " IN (SELECT " + DatabaseConsts.ARTICLE_ID +
                " FROM " + DatabaseConsts.TABLE_TAGS +
                " WHERE " + DatabaseConsts.TAG_NAME + " = ?) " +
                " ORDER BY " + DatabaseConsts.ARTICLE_ID + " DESC " +
                " LIMIT " + pageSize + " OFFSET " + (pageNumber-1)*pageSize;
        ArrayList<Object> list = new ArrayList<>();
        list.add(tagName);
        return DbRetrieveUtils.retrieveBeanListByParams(sql, list, ArticleVo.class);
    }
}
