package com.macie.service.serviceImpl;

import com.macie.bean.vo.ArticleVo;
import com.macie.dao.daoImpl.ArticleDaoImpl;
import com.macie.service.ArticleService;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/10/29 -16:57
 */
public class ArticleServiceImpl implements ArticleService {
    private static final ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();

    @Override
    public ArrayList<ArticleVo> listArticles(String queryType, Integer queryPage, Integer pageSize, String queryName) {
        ArrayList<ArticleVo> articleVos = null;

        // 检索所有文章或某个tag,category下的所有文章
        if("all".equals(queryPage)) {
            if("category".equals(queryType) && queryName != null) {
                articleVos = articleDaoImpl.listArticlesByCategoryName(queryName);
            }
            else if("tag".equals(queryType) && queryName != null) {
                articleVos = articleDaoImpl.listArticlesByTagName(queryName);
            }
            else{
                articleVos = articleDaoImpl.listAllArticles();
            }
        }
        // 检索某一页的文章或某个tag,category下的某一页的文章
        else if(queryPage != null && queryPage != null && pageSize != null) {

            if("category".equals(queryType) && queryName != null){
                articleVos = articleDaoImpl.listArticlesByCategoryNamePerPage(queryName, queryPage, pageSize);
            }
            else if("tag".equals(queryType) && queryName != null){
                articleVos = articleDaoImpl.listArticlesByTagNamePerPage(queryName, queryPage, pageSize);
            }
            else if("article".equals(queryType)){
                articleVos = articleDaoImpl.listArticlesPerPage(queryPage, pageSize);
            }
        }
        return articleVos;
    }

    @Override
    public ArticleVo getArticle(Integer articleId) {
        if(articleId == null) {
            return null;
        }
        ArticleVo articleVo = articleDaoImpl.getArticleById(articleId);
        return articleVo;
    }

    @Override
    public ArticleVo getPreviousArticle(Integer articleId) {
        if(articleId == null) {
            return null;
        }
        ArticleVo prevArticleVo = articleDaoImpl.getPreviousArticle(articleId);
        return prevArticleVo;
    }

    @Override
    public ArticleVo getNextArticle(Integer articleId) {
        if(articleId == null) {
            return null;
        }
        ArticleVo nextArticleVo = articleDaoImpl.getNextArticle(articleId);
        return nextArticleVo;
    }

    @Override
    public Long countArticles(String queryType, String queryName) {
        Long totalCount = articleDaoImpl.getArticlesTotalCount();
        if("category".equals(queryType) && queryName != null){

            totalCount = articleDaoImpl.getArticlesTotalCount("category", queryName);
        }
        else if("tag".equals(queryType) && queryName != null){
            totalCount = articleDaoImpl.getArticlesTotalCount("tag", queryName);
        }
        return totalCount;
    }

    @Override
    public int updateArticleViewCount(Integer articleId) {
        return articleDaoImpl.updateArticleViewCount(articleId);
    }
}
