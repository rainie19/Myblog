package com.macie.service;

import com.macie.dao.daoImpl.ArticleDaoImpl;
import com.macie.dao.daoImpl.CategoryDaoImpl;
import com.macie.dao.daoImpl.TagDaoImpl;

import java.util.TreeMap;

/**
 * @author Macie
 * @date 2020/10/23 -19:31
 */
public class AllCountService {
    private static final ArticleDaoImpl articleDaoImpl = new ArticleDaoImpl();
    private static final CategoryDaoImpl categoryDaoImpl = new CategoryDaoImpl();
    private static final TagDaoImpl tagDaoImpl = new TagDaoImpl();

    /**
     * 获取概览文章,分类，标签数
     *
     * @return
     */
    public TreeMap<String, Long> getAllCount() {
        TreeMap<String, Long> countMap = new TreeMap<>();
        Long articlesTotalCount = articleDaoImpl.countAllArticles();
        Long categoriesTotalCount = categoryDaoImpl.countAllCategories();
        Long tagsTotalCount = tagDaoImpl.countAllTags();
        countMap.put("articlesTotalCount", articlesTotalCount);
        countMap.put("categoriesTotalCount", categoriesTotalCount);
        countMap.put("tagsTotalCount", tagsTotalCount);
        return countMap;
    }
}
