package com.macie.dao;

import com.macie.bean.vo.ArticleVo;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/9/25 -17:24
 */
public interface ArticleDao {
    /**
     *
     * @return 所有文章信息
     */
    public ArrayList<ArticleVo> listAllArticles();

    /**
     * 返回某一页的文章
     * @return
     */
    public ArrayList<ArticleVo> listArticlesPerPage(Integer pageNumber, int pageSize);
    /**
     * 根据articl_id查询文章信息
     * @param articleId
     * @return 具体博客
     */
    public ArticleVo getArticleById(Integer articleId);

    /**
     * 增加一篇文章
     * @param articleVo
     * @return 返回增加文章的id
     */
    public int insertNewArticle(ArticleVo articleVo);

    /**
     * 修改文章内容
     * @param articleVo
     * @return
     */
    public int updateArticle(ArticleVo articleVo);

    /**
     * 根据id更新文章阅读次数
     * @param articleId
     * @return 返回更新结果
     */
    public int updateArticleViewCount(Integer articleId);

    /**
     *删除文章
     * @param articleId
     * @return
     */
    public int deleteArticle(Integer articleId);

    /**
     * 获取前一篇文章
     * @param articleId
     * @return
     */
    public ArticleVo getPreviousArticle(Integer articleId);
    /**
     * 查找下一篇文章
     * @param articleId
     * @return
     */
    public ArticleVo getNextArticle(Integer articleId);

    /**
     * 获取文章总数
     * @return
     */
    public Long getArticlesTotalCount();

    /**
     * 获取某个type下的所有文章数
     * @param type 分类或标签
     * @param value
     * @return
     */
    public Long getArticlesTotalCount(String type, String value);
    /**
     *获取某分类下的所有文章
     * @return 某分类下的所有文章
     */
    public ArrayList<ArticleVo> listArticlesByCategoryName(String categoryName);

    /**
     * 获取某分类下某一页的文章
     * @param categoryName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public ArrayList<ArticleVo> listArticlesByCategoryNamePerPage(String categoryName, Integer pageNumber, Integer pageSize);

    /**
     *获取某标签下的所有文章
     * @return 某标签下的所有文章
     */
    public ArrayList<ArticleVo> listArticlesByTagName(String tagName);

    /**
     * 获取某标签下某一页的文章
     * @param tagName
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public ArrayList<ArticleVo> listArticlesByTagNamePerPage(String tagName, Integer pageNumber, Integer pageSize);

}
