package com.macie.service;

import com.macie.bean.vo.ArticleVo;
import com.macie.dao.ArticleDao;
import com.macie.dao.daoImpl.ArticleDaoImpl;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/9/26 -0:24
 */
public class ArticleService_Old implements ArticleDao {
    private static final ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
    @Override
    public ArrayList<ArticleVo> listAllArticles() {
        return articleDaoImpl.listAllArticles();
    }

    @Override
    public ArrayList<ArticleVo> listArticlesPerPage(Integer pageNumber, int pageSize) {
        return articleDaoImpl.listArticlesPerPage(pageNumber, pageSize);
    }

    @Override
    public ArticleVo getArticleById(Integer articleId) {
        return articleDaoImpl.getArticleById(articleId);
    }

    @Override
    public int insertNewArticle(ArticleVo articleVo) {
        return articleDaoImpl.insertNewArticle(articleVo);
    }

    @Override
    public int updateArticle(ArticleVo articleVo) {
        return articleDaoImpl.updateArticle(articleVo);
    }

    @Override
    public int updateArticleViewCount(Integer articleId) {
        return articleDaoImpl.updateArticleViewCount(articleId);
    }

    @Override
    public int deleteArticle(Integer articleId) {
        return articleDaoImpl.deleteArticle(articleId);
    }

    @Override
    public ArticleVo getPreviousArticle(Integer articleId) {
        return articleDaoImpl.getPreviousArticle(articleId);
    }

    @Override
    public ArticleVo getNextArticle(Integer articleId) {
        return articleDaoImpl.getNextArticle(articleId);
    }

    @Override
    public Long getArticlesTotalCount() {
        return articleDaoImpl.getArticlesTotalCount();
    }

    @Override
    public Long getArticlesTotalCount(String type, String value) {
        return articleDaoImpl.getArticlesTotalCount(type, value);
    }

    @Override
    public ArrayList<ArticleVo> listArticlesByCategoryName(String categoryName) {
        return articleDaoImpl.listArticlesByCategoryName(categoryName);
    }

    @Override
    public ArrayList<ArticleVo> listArticlesByCategoryNamePerPage(String categoryName, Integer pageNumber, Integer pageSize) {
        return articleDaoImpl.listArticlesByCategoryNamePerPage(categoryName, pageNumber, pageSize);
    }

    @Override
    public ArrayList<ArticleVo> listArticlesByTagName(String tagName) {
        return articleDaoImpl.listArticlesByTagName(tagName);
    }

    @Override
    public ArrayList<ArticleVo> listArticlesByTagNamePerPage(String tagName, Integer pageNumber, Integer pageSize) {
        return articleDaoImpl.listArticlesByTagNamePerPage(tagName, pageNumber, pageSize);
    }
}
