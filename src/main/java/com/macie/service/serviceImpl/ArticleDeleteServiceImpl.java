package com.macie.service.serviceImpl;

import com.macie.dao.daoImpl.ArticleDaoImpl;
import com.macie.dao.daoImpl.TagDaoImpl;
import com.macie.service.ArticleDeleteService;

/**
 * @author Macie
 * @date 2020/10/29 -18:12
 */
public class ArticleDeleteServiceImpl implements ArticleDeleteService {
    private static final ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
    private static final TagDaoImpl tagDaoImpl = new TagDaoImpl();

    @Override
    public int deleteArticle(Integer articleId) {
        int ret = -1;
        if (articleId == null) {
            return ret;
        }
        ret = articleDaoImpl.deleteArticle(articleId);
        // 删除文章后需要把文章对应的标签也删掉
        tagDaoImpl.deleteTagsByArticleId(articleId);
        return ret;
    }
}
