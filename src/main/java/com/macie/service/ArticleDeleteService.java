package com.macie.service;

/**
 * @author Macie
 * @date 2020/10/29 -10:24
 */
public interface ArticleDeleteService {
    /**
     * 删除文章
     * @param articleId
     * @return
     */
    public int deleteArticle(Integer articleId);
}
