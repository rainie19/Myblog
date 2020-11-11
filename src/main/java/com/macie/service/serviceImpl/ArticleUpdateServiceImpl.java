package com.macie.service.serviceImpl;

import com.macie.bean.vo.ArticleVo;
import com.macie.bean.vo.CategoryVo;
import com.macie.dao.daoImpl.ArticleDaoImpl;
import com.macie.dao.daoImpl.CategoryDaoImpl;
import com.macie.dao.daoImpl.TagDaoImpl;
import com.macie.service.ArticleUpdateService;

import java.util.ArrayList;

/**
 * @author Macie
 * @date 2020/10/30 -17:19
 */
public class ArticleUpdateServiceImpl implements ArticleUpdateService {
    private static final ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
    private static final TagDaoImpl tagDaoImpl = new TagDaoImpl();
    private static final CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();

    @Override
    public ArticleVo publishArticle(ArticleVo articleVo, ArrayList<String> tagList, String publishType) {
        if (articleVo == null || publishType == null) {
            return null;
        }
        Integer articleId = null;
        // 若是创造了新的分类，先将此分类信息插入数据库中
        String categoryName = articleVo.getCategoryName();
        if (!categoryDaoImpl.isCategoryExits(categoryName)) {
            CategoryVo categoryVo = new CategoryVo();
            categoryVo.setCategoryName(categoryName);
            categoryDaoImpl.insertNewCategory(categoryVo);
        }

        if("create".equals(publishType)) {
            articleId = articleDaoImpl.insertNewArticle(articleVo);
        }
        else if("modify".equals(publishType)) {
            articleDaoImpl.updateArticle(articleVo);
            articleId = articleVo.getArticleId();
        }
        // 插入文章的tags
        if(articleId != null) {
            tagDaoImpl.deleteTagsByArticleId(articleId);
            for (String tag : tagList) {
                tagDaoImpl.insertTagByArticleId(articleId, tag);
            }
        }
        ArticleVo reponseArticleVo = new ArticleVo();
        reponseArticleVo.setArticleSlug(articleVo.getArticleSlug());
        reponseArticleVo.setArticleId(articleId);
        return reponseArticleVo;
    }
}
