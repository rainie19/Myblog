package com.macie.service;

import com.macie.bean.vo.ArticleVo;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/10/28 -16:55
 */
public interface ArticleService {
    /**
     *
     * @param queryType 查询的类型
     * @param queryPage 查询的页码
     * @param queryName 查询类型的具体名字
     * @return
     */
    public ArrayList<ArticleVo>  listArticles(String queryType, Integer queryPage, Integer pageSize, String queryName);

    /**
     * 获取一篇文章
     * @param articleId
     * @return
     */
    public ArticleVo getArticle(Integer articleId);

    /**
     * 获取前一篇文章
     * @param articleId
     * @return
     */
    public ArticleVo getPreviousArticle(Integer articleId);

    /**
     * 获取后一篇文章
     * @param articleId
     * @return
     */
    public ArticleVo getNextArticle(Integer articleId);

    /**
     * 获取某个类型下的文章总数
     * @param queryType
     * @param queryName
     * @return
     */
    public Long countArticles(String queryType, String queryName);

    /**
     * 根据id更新文章阅读次数
     * @param articleId
     * @return 返回更新结果
     */
    public int updateArticleViewCount(Integer articleId);
}
