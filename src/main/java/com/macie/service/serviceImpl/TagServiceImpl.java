package com.macie.service.serviceImpl;

import com.macie.bean.vo.ArticleVo;
import com.macie.bean.vo.TagVo;
import com.macie.dao.TagDao;
import com.macie.dao.daoImpl.TagDaoImpl;
import com.macie.service.TagService;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * @author Macie
 * @date 2020/10/29 -17:59
 */
public class TagServiceImpl implements TagService, TagDao {
    private static final TagDaoImpl tagDaoImpl = new TagDaoImpl();
    @Override
    public TreeMap<Integer, ArrayList<TagVo>> getArticleTagMap(ArrayList<ArticleVo> articleVos) {
        TreeMap<Integer, ArrayList<TagVo>>  tMap = new TreeMap<>();
        for(ArticleVo articleVo : articleVos) {
            Integer article_id = articleVo.getArticleId();
            ArrayList<TagVo> tagVoArrayList = tagDaoImpl.retrieveTagsByArticleId(article_id);
            tMap.put(article_id, tagVoArrayList);
        }
        return tMap;
    }

    @Override
    public ArrayList<TagVo> listAllTags() {
        return tagDaoImpl.listAllTags();
    }

    @Override
    public Long countAllTags() {
        return tagDaoImpl.countAllTags();
    }

    @Override
    public ArrayList<TagVo> retrieveTagsByArticleId(Integer articleId) {
        return tagDaoImpl.retrieveTagsByArticleId(articleId);
    }

    @Override
    public int deleteTagsByArticleId(Integer articleId) {
        return tagDaoImpl.deleteTagsByArticleId(articleId);
    }

    @Override
    public int insertTagByArticleId(Integer articleId, String tagName) {
        return tagDaoImpl.insertTagByArticleId(articleId, tagName);
    }

    @Override
    public int insertNewTag(String tagName) {
        return tagDaoImpl.insertNewTag(tagName);
    }

    @Override
    public int deleteTag(String tagName) {
        return tagDaoImpl.deleteTag(tagName);
    }
}
